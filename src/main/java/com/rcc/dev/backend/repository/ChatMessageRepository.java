package com.rcc.dev.backend.repository;

import com.rcc.dev.backend.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
