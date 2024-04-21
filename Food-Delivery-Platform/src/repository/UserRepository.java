package repository;

import java.util.HashMap;

import model.User;

public class UserRepository {
    private final HashMap<Integer, User> userDataBase = new HashMap<>();

    public boolean addUser(User user) {
        if (userDataBase.containsKey(user.getUserId())) {
            return false;
        }else {
            userDataBase.put(user.getUserId(), user);
        }
        return true;
    }

    public boolean updateUser(User user) {
        if (userDataBase.containsKey(user.getUserId())) {
            userDataBase.put(user.getUserId(), user);
            return true;
        }
        return false;
    }

    public boolean deleteUser(User user) {
        if(userDataBase.containsKey(user.getUserId())) {
            userDataBase.remove(user.getUserId());
            return true;
        }
        return false;
    }

    public User getUser(String userId) {
        return userDataBase.get(userId);
    }

}
