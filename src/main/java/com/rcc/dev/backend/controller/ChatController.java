//package com.rcc.dev.backend.controller;
//
//import com.rcc.dev.backend.model.ChatMessage;
//import com.rcc.dev.backend.service.chat.iservice.ChatMessageService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class ChatController {
//    private final SimpMessagingTemplate messagingTemplate;
//    private final ChatMessageService chatMessageService;
//
//    @MessageMapping("/chat")
//    public void processMessage(@Payload ChatMessage chatMessage) {
//        ChatMessage savedMsg = chatMessageService.save(chatMessage);
//        messagingTemplate.convertAndSendToUser(
//                chatMessage.getRecipientId(), "/queue/messages", savedMsg
//        );
//    }
//
//    @GetMapping("/messages/{senderId}/{recipientId}")
//    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId, @PathVariable String recipientId) {
//        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
//    }
//}
