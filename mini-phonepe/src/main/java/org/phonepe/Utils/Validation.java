package org.phonepe.Utils;

import org.phonepe.model.User;

public class Validation {

    public static boolean checkUserData(User user) {
        if (user.getUserId() == null || user.getUserName() == null) {
            return true;
        }
        return false;
    }
}
