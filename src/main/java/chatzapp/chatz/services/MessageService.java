package chatzapp.chatz.services;

import chatzapp.chatz.data.models.Chat;
import chatzapp.chatz.data.models.Message;
import chatzapp.chatz.dtos.SendMessageRequest;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    Message createMessage(SendMessageRequest sendMessageRequest);
    void deleteMessage(String chatId);
    Optional<Message> findMessage(String chatId);

}
