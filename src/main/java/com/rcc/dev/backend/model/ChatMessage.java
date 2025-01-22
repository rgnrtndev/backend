//package com.rcc.dev.backend.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Date;
//import java.util.UUID;
//
//@Entity
//@Table(name = "chat_message")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class ChatMessage {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id; // ID tipe Long, diubah dari String
//    private String chatId;
//    private Long senderId;
//    private Long recipientId;
//    private String content;
//    private Date timestamp;
//}
