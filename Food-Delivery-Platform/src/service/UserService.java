package service;

import exception.ExceptionType;
import exception.UserException;
import model.User;
import repository.UserRepository;

public class UserService {

    public UserRepository userRepository;

    public boolean addUser(User user) {
        boolean isUserAdded = userRepository.addUser(user);
        if (!isUserAdded)  {
            throw new UserException(ExceptionType.USER_ALREADY_EXISTS);
        }
        return isUserAdded;
    }

    public User getUserDetails(String userId) {
        User user = userRepository.getUser(userId);
        if(user == null) {
            throw new UserException(ExceptionType.USER_NOT_FOUND);
        }
        return user;
    }

    public boolean updateUser(User user) {
        boolean isUpdated = userRepository.updateUser(user);
        if (!isUpdated)  {
            throw new UserException(ExceptionType.USER_NOT_FOUND);
        }
        return isUpdated;
    }

    public boolean removeUser(User user) {
        boolean isRemoved = userRepository.deleteUser(user);
        if (!isRemoved) {
            throw new UserException(ExceptionType.USER_NOT_FOUND);
        }
        return isRemoved;
    }
}
