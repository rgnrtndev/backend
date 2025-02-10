package com.rcc.dev.backend.service.chat.iservice;

import com.rcc.dev.backend.model.ChatMessage;
import com.rcc.dev.backend.model.User;

import java.util.List;

public interface ChatService {
    ChatMessage save(ChatMessage chatMessage);
    List<ChatMessage> findChatMessages(String senderId, String recipientId);
}
