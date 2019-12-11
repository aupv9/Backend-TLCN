package datve.com.dao.user;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import datve.com.config.MongoFactory;
import datve.com.dao.ve.VeDaoImpl;
import datve.com.model.User;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private MongoCollection coll = MongoFactory.getCollection("datvexe", "user");

    @Override
    public boolean checkLogout(String username) {
        for (User userExist : findAll()) {
            if (StringUtils.equals(username, userExist.getUsername())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(User user) {
        for (User userExist : findAll()) {
            if (StringUtils.equals(user.getUsername(), userExist.getUsername())
                    && StringUtils.equals(user.getPassword(), userExist.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean signUp(User user) {

        try {

            coll.insertOne(new Document("_id",user.get_id())
                    .append("username", user.getUsername())
                    .append("password", user.getPassword())
                    .append("roles", Arrays.asList("ROLE_USER"))
                    .append("deleted", false));
            return true;
        } catch (MongoException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean editUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public boolean addUser(User user) {
        try {
            coll.insertOne(new Document("_id",user.get_id())
                    .append("username", user.getUsername())
                    .append("password", user.getPassword())
                    .append("roles", user.getRoles())
                    .append("deleted", false));
            return true;
        } catch (MongoException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public User loadUserByUsername(String username) {
        for (User user : findAll()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        MongoCursor<Document> cursor = coll.find().iterator();
        List<User> list = new ArrayList<User>();
        while (cursor.hasNext()) {
            User user = new User();
            Document doc = cursor.next();
            user.set_id(doc.get("_id").toString());
            user.setUsername(doc.get("username").toString());
            user.setPassword(doc.get("password").toString());
            user.setRoles((List<String>) doc.get("roles"));
            user.setDeleted((Boolean) doc.get("deleted"));
            list.add(user);
        }
        return list;
    }
}
