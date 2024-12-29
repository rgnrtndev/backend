//package com.rcc.dev.backend.service.chat.impl;
//
//import com.rcc.dev.backend.constant.Status;
//import com.rcc.dev.backend.model.User;
//import com.rcc.dev.backend.repository.UserRepository;
//import com.rcc.dev.backend.service.chat.iservice.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public void disconnect(User user) {
//        var userDetail = userRepository.findById(user.getId());
//    }
//
//    @Override
//    public User saveUser(User user) {
////        user.setStatus(Status.ONLINE);
//        return userRepository.save(user);
//    }
//
//    @Override
//    public List<User> findConnectedUsers() {
//        return null;
////        return userRepository.findAllByStatus(Status.ONLINE);
//    }
//}
