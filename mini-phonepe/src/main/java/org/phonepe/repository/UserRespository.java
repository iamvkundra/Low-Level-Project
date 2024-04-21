package org.phonepe.repository;

import java.util.HashMap;
import java.util.List;

import org.phonepe.model.User;

public class UserRespository {

    HashMap<String, User> userData = new HashMap<>();

    public boolean registerUser(User user) {
        if (userData.containsKey(user.getUserId())) {
            return false;
        }
        this.userData.put(user.getUserId(), user);
        return true;
    }

    public User getUserById(String userId) {
        if (!userData.containsKey(userId)) {
            throw new RuntimeException("No User find with given id: "+ userId);
        }
        return userData.get(userId);
    }

    public List<User> getAllTheUser() {
        return userData.values().stream().toList();
    }
}
