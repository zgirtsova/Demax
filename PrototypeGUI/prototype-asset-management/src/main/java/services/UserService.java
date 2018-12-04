package services;

import daos.UserDao;
import dtos.users.UserDTO;
import entities.User;

public class UserService {
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public UserDTO getUserDTO(int id) {
        User user = userDao.getById(id);

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String role = user.getRole();

        return new UserDTO(firstName, lastName, role);
    }
}