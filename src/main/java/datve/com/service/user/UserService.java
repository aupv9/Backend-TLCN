package datve.com.service.user;


import datve.com.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User loadUserByUsername(String username);

    boolean checkLogin(User user);

    boolean checkLogout(String username);

    boolean addUser(User user);

    boolean editUser(User user);

    boolean deleteUser(int id);
}
