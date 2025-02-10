package com.rcc.dev.backend.service.chat.impl;

import com.rcc.dev.backend.enums.Status;
import com.rcc.dev.backend.model.UserMDB;
import com.rcc.dev.backend.repository.UserMGDRepository;
import com.rcc.dev.backend.service.chat.iservice.UserMGDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMGDServiceImpl implements UserMGDService {

    private final UserMGDRepository repository;

    @Override
    public void saveUser(UserMDB user) {
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    @Override
    public void disconnect(UserMDB user) {
        var storedUser = repository.findById(user.getNickName()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }

    @Override
    public List<UserMDB> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }
}
