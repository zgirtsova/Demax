package orm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Product;
import util.CurrentConnection;

public class TestDao extends Dao<Product> {
    
    private static TestDao _INSTANCE;
    private TestDao() throws SQLException, ClassNotFoundException{
        super();
    } 

    public void makeQuery(String table) throws SQLException, ClassNotFoundException {
       
        PreparedStatement ps = CurrentConnection.getCurrent().prepareStatement("SELECT * FROM "+table);
        ResultSet res = ps.executeQuery();
        while(res.next()){

        }
        return;
    }

    public static final TestDao getInstance() throws ClassNotFoundException, SQLException {
        if(_INSTANCE == null){
            _INSTANCE = new TestDao();
        }
        return _INSTANCE;
    }

}