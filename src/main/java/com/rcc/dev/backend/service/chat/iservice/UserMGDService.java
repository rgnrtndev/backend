package com.rcc.dev.backend.service.chat.iservice;

import com.rcc.dev.backend.model.UserMDB;

import java.util.List;

public interface UserMGDService {
    void saveUser(UserMDB user);
    void disconnect(UserMDB user);
    List<UserMDB> findConnectedUsers();
}
