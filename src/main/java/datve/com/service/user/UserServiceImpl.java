package datve.com.service.user;


import datve.com.dao.user.UserDao;
import datve.com.dao.user.UserDaoImpl;
import datve.com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {


    @Autowired
    UserDaoImpl userDao;

    @Override
    public User loadUserByUsername(String username) {
        return userDao.loadUserByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean signUp(User user) {
        return userDao.signUp(user);
    }

    @Override
    public boolean checkLogout(String username) {
        return userDao.checkLogout(username);
    }

    @Override
    public boolean editUser(User user) {
        return userDao.editUser(user);
    }

    @Override
    public boolean deleteUser(User user) {
        return userDao.deleteUser(user);
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public boolean checkLogin(User user) {
        return userDao.checkLogin(user);
    }
}
