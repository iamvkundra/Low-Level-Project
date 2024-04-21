package org.phonepe.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    String userId;
    String userName;
    public User(String userName) {
        this.userId = UUID.randomUUID().toString();;
    }
}
