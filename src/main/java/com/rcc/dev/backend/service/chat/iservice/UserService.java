package com.rcc.dev.backend.service.chat.iservice;

import com.rcc.dev.backend.model.User;

import java.util.List;

public interface UserService {
    void disconnect(User user);
    User saveUser(User user);
    List<User> findConnectedUsers();
}
