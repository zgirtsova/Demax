package daos;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import annotations.FieldName;
import helpers.FieldData;
import singletons.ConnectionPoolSingleton;

public abstract class BaseDao<T> {

    private String className;
    private String tableName;
    private Class clazz;
    private Object instanceOfClazz;
    private Map<String, FieldData> fieldsByFieldName;
    private Map<String, FieldData> fieldsByColumnName;

    public BaseDao() {
        this.className = this.getClass().getSimpleName().substring(0, this.getClass().getSimpleName().length()-3);
        this.tableName = className.toLowerCase() + (className.charAt(className.length()-1) == 's' ? "es" : "s");
        this.fieldsByFieldName = new LinkedHashMap<>();
        this.fieldsByColumnName = new LinkedHashMap<>();
        this.clazz = null;
        this.instanceOfClazz = null;
    }
 
    private void getInstanceOfClazz() {

        try {

            this.clazz = Class.forName("entities." + this.className);

            Field[] fields = this.clazz.getDeclaredFields();
            Method[] methods = this.clazz.getMethods();

            this.collectFieldsData(fields, methods);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (this.clazz != null) {

            Constructor constructor = null;

            try {

                constructor = this.clazz.getConstructor(null);

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            if (constructor != null) {
                try {

                    this.instanceOfClazz = constructor.newInstance();

                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public T getById(int id) {
 
        this.getInstanceOfClazz();

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection connection = null;

        try {

            connection = ConnectionPoolSingleton.getInstance().getPooledConnection().getConnection();

            String sql = "SELECT * FROM " + this.tableName + " WHERE id = ?";

            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
            }

            if (preparedStatement != null ) {
                rs = preparedStatement.executeQuery();
            }

            ResultSetMetaData meta = rs.getMetaData();

            if (rs.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {

                    String columnName = meta.getColumnName(i);
                    try {
                        this.fieldsByColumnName.get(columnName).getSetter().invoke(this.instanceOfClazz, rs.getObject(i));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return (T) this.instanceOfClazz;
    }

    public boolean insert(T obj) {

        this.getInstanceOfClazz();

        StringBuilder sbColumnNames = new StringBuilder();
        StringBuilder sbValues = new StringBuilder();

        this.fieldsByColumnName.entrySet().stream()
                .forEach(kvp -> {
                    if (! kvp.getKey().equals("id")) {
                        sbColumnNames.append(String.format(", %s",  kvp.getKey()));
                        sbValues.append(", ?");
                    }
                });

        String columnNames = sbColumnNames.toString().substring(1);
        String values = sbValues.toString().substring(1);

        String query = "INSERT INTO " + this.tableName + " (" + columnNames + ") " + "VALUES (" + values + ")";

        return this.editRow(query, obj, false);
    }

    public boolean update(T obj) {

        this.getInstanceOfClazz();

        StringBuilder sbValues = new StringBuilder();

        this.fieldsByColumnName.entrySet().stream()
                .forEach(kvp -> {
                    if (! kvp.getKey().equals("id")) {
                        sbValues.append(String.format(", %s = ?", kvp.getKey()));
                    }
                });

        String values = sbValues.toString().substring(1);

        String query = "UPDATE " + this.tableName + " SET" + values + " WHERE id = ?";

        return this.editRow(query, obj, true);
    }

    public boolean delete(int id) {

        PreparedStatement preparedStatement = null;
        Connection connection = null;

        String query = "DELETE FROM " + this.tableName + " WHERE id = ?";

        boolean success = true;

        try {
            connection = ConnectionPoolSingleton.getInstance().getPooledConnection().getConnection();

            if (connection != null) {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
            }

            if (preparedStatement != null ) {
                success = preparedStatement.executeUpdate() != 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();

            success = false;
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return success;
    }

    private boolean editRow(String query, T obj, boolean update) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        boolean success = true;

        try {
            connection = ConnectionPoolSingleton.getInstance().getPooledConnection().getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(query);
            int i = 1;

            for (Map.Entry<String, FieldData> fieldDataEntry : this.fieldsByFieldName.entrySet()) {
                if (! fieldDataEntry.getKey().equals("id")) {
                    if (fieldDataEntry.getValue().getGetter().getReturnType().getName().equals("java.time.LocalDateTime")) {
                        preparedStatement.setTimestamp(i, Timestamp.valueOf((LocalDateTime) fieldDataEntry.getValue().getGetter().invoke(obj)));
                    } else {
                        preparedStatement.setObject(i, fieldDataEntry.getValue().getGetter().invoke(obj));
                    }

                    i++;
                }
            }

            if (update) {
                preparedStatement.setInt(i, (int) this.fieldsByFieldName.get("id").getGetter().invoke(obj));
            }

            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();

            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            success = false;
        } finally {
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        } 

        return success;
    }

    private Method findMethod(Method[] methods, String fieldName, String prefix) {

        String setterName = prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        for (Method method : methods) {

            if (method.getName().equals(setterName)) {

                return method;
            }
        }

        return null;
    }

    private void collectFieldsData(Field[] fields, Method[] methods) {

        for (Field field : fields) {

            String fieldName = field.getName();
            String columnName = field.getAnnotation(FieldName.class).columnName();
            Method getter = this.findMethod(methods, fieldName, "get");
            Method setter = this.findMethod(methods, fieldName, "set");

            FieldData fieldData = new FieldData();
            fieldData.setFieldName(fieldName);
            fieldData.setColumnName(columnName);
            fieldData.setGetter(getter);
            fieldData.setSetter(setter);

            this.fieldsByFieldName.put(fieldName, fieldData);
            this.fieldsByColumnName.put(columnName, fieldData);
        }

    }

    protected String getTableName() {
        return this.tableName;
    }
}
