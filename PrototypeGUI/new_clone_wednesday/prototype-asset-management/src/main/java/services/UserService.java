package services;

import daos.UserDao;
import dtos.users.UserDTO;
import entities.Project;
import entities.User;
import helpers.ModelMapper;
import singletons.ConnectionPoolSingleton;

import java.sql.SQLException;
import java.util.EnumMap;

public class UserService {

    private UserDao userDao;
    private ModelMapper modelMapper;
    private User user;

    public UserService() {
        this.userDao = new UserDao();
        this.modelMapper = new ModelMapper();
    }

    public UserDTO getUserDTO(int id) {
        //BEGIN
        ConnectionPoolSingleton.getInstance().begin();
        try {
            user = userDao.getById(id);

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();

        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }

        return (UserDTO) modelMapper.map(user, UserDTO.class);
    }

    //TODO new logic added
    public UserDTO getByEmailAndPassword(String email, String password) {
        //BEGIN
        ConnectionPoolSingleton.getInstance().begin();
        try {
            user = userDao.getByEmailAndPassword(email, password);

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();

        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }

        return (UserDTO) modelMapper.map(user, UserDTO.class);
    }

    public void updatePassword(UserDTO userDTO, String oldPass, String newPass ) throws Exception {

        //BEGIN
        ConnectionPoolSingleton.getInstance().begin();
        try {
            user = (User)modelMapper.map(userDTO, User.class);

            if (!user.getPassword().equals(oldPass)) {
                throw new Exception("Old password is incorrect!");
            } else {
                user.setPassword(newPass);
                userDao.update(user);
            }

            //COMMIT
            ConnectionPoolSingleton.getInstance().commit();

        } catch (Exception e) {
            e.printStackTrace(System.out);

            //ROLLBACK
            ConnectionPoolSingleton.getInstance().rollback();
        }

    }

}