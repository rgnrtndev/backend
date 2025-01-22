//package com.rcc.dev.backend.service.chat.impl;
//
//import com.rcc.dev.backend.model.ChatRoom;
//import com.rcc.dev.backend.repository.ChatRoomRepository;
//import com.rcc.dev.backend.repository.UserRepository;
//import com.rcc.dev.backend.service.chat.iservice.ChatRoomService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class ChatRoomServiceImpl implements ChatRoomService {
//
//    private final ChatRoomRepository chatRoomRepository;
//    private final UserRepository userRepository;
//
//    @Override
//    public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists) {
//        var sender = userRepository.findById(Long.valueOf(senderId));
//        var recipient = userRepository.findById(Long.valueOf(recipientId));
//        if(sender.isPresent() && recipient.isPresent()){
//            var senderNickName = sender.get().getUsername();
//            var recipientNickName = recipient.get().getUsername();
//
//            var chatRoom = chatRoomRepository.findBySenderIdAndRecipientId(senderNickName, recipientNickName);
//            if(chatRoom.isPresent()){
//                return Optional.of(chatRoom.get().getChatId());
//            }else if(createNewRoomIfNotExists){
//                var chatId = createChatId(senderNickName, recipientNickName);
//                return Optional.of(chatId);
//            }
//        }
//        return Optional.empty();
//    }
//
//    private String createChatId(String senderName, Long senderId, String recipientName, Long recipientId) {
//        var chatId = String.format("%s_%s", senderId, recipientId);
//
//        ChatRoom senderRecipient = ChatRoom
//                .builder()
//                .chatId(chatId)
//                .senderId(senderId)
//                .recipientId(r)
//                .build();
//
//        ChatRoom recipientSender = ChatRoom
//                .builder()
//                .chatId(chatId)
//                .senderId(recipientId)
//                .recipientId(senderId)
//                .build();
//
//        chatRoomRepository.save(senderRecipient);
//        chatRoomRepository.save(recipientSender);
//
//        return chatId;
//    }
//}
