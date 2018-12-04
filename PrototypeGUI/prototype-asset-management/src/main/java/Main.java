import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import daos.*;
import entities.*;

import javax.security.auth.callback.Callback;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        //BaseDao<Product> productDao = new ProductDao();
        ProductDao productDao = new ProductDao();
        //BaseDao<Project> projectDao = new ProjectDao();
        ProjectDao projectDao = new ProjectDao();
        ActionDao actionDao = new ActionDao();
        BaseDao<User> userDao = new UserDao();
        BaseDao<Status> statusDao = new StatusDao();
        //daos.BaseDao<entities.Picture> pictureDao = new daos.PictureDao();
        PictureDao pictureDao = new PictureDao();

        Map.Entry<List<Project>, Integer> rs = projectDao.getPerPage(5, 1);

        System.out.println("finished");

        ThreadLocal<Connection> connectionThreadLocal =  new ThreadLocal<>();
    }
}