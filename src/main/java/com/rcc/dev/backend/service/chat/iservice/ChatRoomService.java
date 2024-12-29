package com.rcc.dev.backend.service.chat.iservice;

import java.util.Optional;

public interface ChatRoomService {
    Optional<String> getChatRoomId(
            String senderId,
            String recipientId,
            boolean createNewRoomIfNotExists
    );
}
