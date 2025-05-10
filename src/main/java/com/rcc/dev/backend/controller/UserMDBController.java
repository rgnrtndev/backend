package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.model.UserMDB;
import com.rcc.dev.backend.service.chat.iservice.UserMGDService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserMDBController {
    private final UserMGDService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public UserMDB addUser(
            @Payload UserMDB user
    ) {
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public UserMDB disconnectUser(
            @Payload UserMDB user
    ) {
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserMDB>> findConnectedUsers() {
        return ResponseEntity.ok(userService.findConnectedUsers());
    }
}
