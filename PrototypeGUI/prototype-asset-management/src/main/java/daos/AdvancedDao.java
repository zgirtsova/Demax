package daos;

import helpers.CustomEntry;
import helpers.Filter;
import helpers.FilterNames;
import helpers.SortParam;
import singletons.ConnectionPoolSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AdvancedDao<T> extends BaseDao<T> {

    protected List<T> getByOwnerId (String owner, int id) {

        return this.getByOwnerId(owner, id, "");
    }

    protected List<T> getByOwnerId (String owner, int id, String limitQuery) {

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection connection = null;

        List<T> result = new ArrayList<>();

        String query = "SELECT * FROM " + super.getTableName() + " WHERE " + owner + " = ? " + limitQuery;

        try {

            connection = ConnectionPoolSingleton.getInstance().getPooledConnection().getConnection();

            if (connection != null) {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
            }

            if (preparedStatement != null ) {
                rs = preparedStatement.executeQuery();
            }

            if (rs != null) {
                while (rs.next()) {
                    int productId = rs.getInt("id");
                    T obj = super.getById(productId);

                    result.add(obj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return result;
    }
    
    protected int getCountByFilterParam (String param, int id) {

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Connection connection = null;

        int count = 0;

        String query = "SELECT count(id) FROM " + super.getTableName() + " WHERE " + param + " = ?";

        try {

            connection = ConnectionPoolSingleton.getInstance().getPooledConnection().getConnection();

            if (connection != null) {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
            }

            if (preparedStatement != null ) {
                rs = preparedStatement.executeQuery();
            }

            if (rs != null) {
                while (rs.next()) {
                    count = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return count;
    }

    /**
     * Returns true if all requests for all ids are true. If only one request is false, returns false.
     * @param ids
     */
    public boolean deleteByIds(int[] ids) {
        
        boolean result = true;

        for (Integer id : ids) {
            result = result && super.delete(id);
        }

        return result;
    }

    /**
     * Returns true if all requests for all ids are true. If only one request is false, returns false.
     */
    public boolean updateStatusByIds(int[] ids, int statusId) {

        boolean result = true;   

        for (Integer id : ids) {
            result = result && this.updateStatusById(id, statusId);
        }

        return result;
    }

    private boolean updateStatusById(int id, int statusId) {

        PreparedStatement preparedStatement = null;
        Connection connection = null;

        boolean success = true;

        String query = "UPDATE " + super.getTableName() + " SET status_id = ? WHERE id = ? ";

        try {

            connection = ConnectionPoolSingleton.getInstance().getPooledConnection().getConnection();

            if (connection != null) {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, statusId);
                preparedStatement.setInt(2, id);
            }

            if (preparedStatement != null ) {
                preparedStatement.executeUpdate();
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

    /**
     * Get items per page with default filter/sort settings:
     * -> Getting the last items added to the database
     */
    public Map.Entry<List<T>, Integer> getPerPage(int resultsPerPage, int page) {

        return this.getPerPage(resultsPerPage, page, null, null);
    }

    /**
     * Get items per page with custom filter and default sort settings
     * -> Getting the filtered items, sorted by date in descending order
     */
    public Map.Entry<List<T>, Integer> getPerPage(int resultPerPage, int page, List<Filter> filters) {

        return this.getPerPage(resultPerPage, page, filters, null);
    }

    /**
     * Get items per page with default filter and custom sort settings
     * -> Getting the items with custom sort
     */
    public Map.Entry<List<T>, Integer> getPerPage(int resultPerPage, int page, SortParam sortParam) {

        return this.getPerPage(resultPerPage, page, null, sortParam);
    }

    /**
     * Get items per page with custom filter/sort settings
     */
    public Map.Entry<List<T>, Integer> getPerPage(int resultsPerPage, int page, List<Filter> filters, SortParam sortParam) {

        PreparedStatement psList = null, psCount = null;
        ResultSet rsList = null, rsCount = null;
        Connection connection = null;

        List<T> list = new ArrayList<>();
        int count = 0;

        int offset = resultsPerPage * (page - 1);

        String queryList = this.prepareGetPerPageQuery(filters, sortParam);
        String queryCount = this.prepareFilteredCountQuery(queryList);

        try {

            connection = ConnectionPoolSingleton.getInstance().getPooledConnection().getConnection();

            if (connection != null) {
                psList = connection.prepareStatement(queryList);
                psCount = connection.prepareStatement(queryCount);

                int index = 0;

                if (filters != null) {
                    for (int i = 0; i < filters.size(); i++) {
                        index = i + 1;
                        if (filters.get(i).getName().equals(FilterNames.DATE_FROM) || filters.get(i).getName().equals(FilterNames.DATE_TO)) {
                            psList.setTimestamp(index, Timestamp.valueOf((LocalDateTime) filters.get(i).getValue()));
                            psCount.setTimestamp(index, Timestamp.valueOf((LocalDateTime) filters.get(i).getValue()));
                        } else {
                            psList.setObject(index, filters.get(i).getValue());
                            psCount.setObject(index, filters.get(i).getValue());
                        }
                    }
                }

                psList.setInt(++index, resultsPerPage);
                psList.setInt(++index, offset);
            }

            if (psList != null ) {
                rsList = psList.executeQuery();
            }

            if (psCount != null) {
                rsCount = psCount.executeQuery();
            }

            if (rsList != null) {
                while (rsList.next()) {
                    int id = rsList.getInt("id");
                    T obj = super.getById(id);

                    list.add(obj);
                }
            }

            if (rsCount != null) {
                if (rsCount.next()) {
                    count = rsCount.getInt("total_count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rsList != null) rsList.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (rsCount != null) rsCount.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (psList != null) psList.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (psCount != null) psCount.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (connection != null) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        list = list.size() == 0 ? null : list;

        return new CustomEntry<>(list, count);
    }

    private String prepareGetPerPageQuery(List<Filter> filters, SortParam sortParam) {

        String defaultSort = "date_created DESC";

        String sortQuery = sortParam == null ? defaultSort : (sortParam.getColumn() + (sortParam.isAscending() ? " ASC" : " DESC"));

        StringBuilder filterQuerySb = new StringBuilder();

        if (filters != null) {
            filterQuerySb.append(" WHERE ");

            for (Filter filter : filters) {

                filterQuerySb.append(filter.getColumnName());

                if (filter.getName().equals(FilterNames.DATE_FROM)) {
                    filterQuerySb.append(" >= ? ");
                } else if (filter.getName().equals(FilterNames.DATE_TO)) {
                    filterQuerySb.append(" <= ? ");
                } else {
                    filterQuerySb.append(" = ? ");
                }

                filterQuerySb.append(" AND ");
            }
        }

        String filterQuery = filterQuerySb.toString().equals("") ? "" : filterQuerySb.toString().substring(0, filterQuerySb.toString().length() - 5);

        if (super.getTableName().equals("projects")) {
            return  "SELECT prj.*, coalesce(t.count, 0 ) as products_count" +
                    " FROM projects AS prj LEFT JOIN (SELECT project_id, count(id) AS count FROM products GROUP BY project_id) AS t ON prj.id = t.project_id" +
                    filterQuery +
                    " ORDER BY " + sortQuery +
                    " LIMIT ? OFFSET ?";
        }

        return  "SELECT *" +
                " FROM " +  super.getTableName() +
                filterQuery +
                " ORDER BY " + sortQuery +
                " LIMIT ? OFFSET ?";
    }

    private String prepareFilteredCountQuery(String getPerPageQuery) {

        int indexFrom = getPerPageQuery.indexOf(" FROM");
        int indexOrder = getPerPageQuery.indexOf(" ORDER");
        return "SELECT count(id) AS total_count " + getPerPageQuery.substring(indexFrom, indexOrder);

    }
}
