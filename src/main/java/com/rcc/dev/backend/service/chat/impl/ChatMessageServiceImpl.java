//package com.rcc.dev.backend.service.chat.impl;
//
//import com.rcc.dev.backend.model.ChatMessage;
//import com.rcc.dev.backend.repository.ChatMessageRepository;
//import com.rcc.dev.backend.service.chat.iservice.ChatMessageService;
//import com.rcc.dev.backend.service.chat.iservice.ChatRoomService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ChatMessageServiceImpl implements ChatMessageService {
//    private final ChatMessageRepository repository;
//    private final ChatRoomService chatRoomService;
//
//    @Override
//    public ChatMessage save(ChatMessage chatMessage) {
//        var chatId = chatRoomService
//                .getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
//                .orElseThrow();
//        chatMessage.setChatId(chatId);
//        repository.save(chatMessage);
//        return chatMessage;
//    }
//
//    @Override
//    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
//        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
//        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
//    }
//}
