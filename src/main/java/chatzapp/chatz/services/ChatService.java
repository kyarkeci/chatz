package chatzapp.chatz.services;

import chatzapp.chatz.data.models.Chat;
import chatzapp.chatz.data.models.Message;
import chatzapp.chatz.dtos.SendMessageRequest;

import java.util.Optional;

public interface ChatService {
    Chat createChat(SendMessageRequest sendMessageRequest);
    Chat addMessageToChat(SendMessageRequest sendMessageRequest);
    Optional<Chat> findChat(String senderChatName, String receiverChatName);
   // Optional<Chat> findChat(String chatName);

}
