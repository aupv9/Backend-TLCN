package datve.com.dao.user;

import datve.com.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    User loadUserByUsername(String user);

    boolean checkLogin(User user);

    boolean checkLogout(String username);

    boolean signUp(User user);

    boolean editUser(User user);

    boolean deleteUser(User user);

    boolean addUser(User user);
}
