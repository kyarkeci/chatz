package chatzapp.chatz.services;

import chatzapp.chatz.data.models.Chat;
import chatzapp.chatz.data.models.User;
import chatzapp.chatz.dtos.RegisterUserRequest;
import chatzapp.chatz.dtos.SendMessageRequest;

import java.util.Map;
import java.util.Optional;

public interface UserService {

    User register(RegisterUserRequest registerUserRequest);

    Optional<User> findByEmail(String email);

    User sendMessage(SendMessageRequest sendMessageRequest);
    Optional<User> findByUserName(String userName);
    Map<String, Chat> viewChat(String senderName, String receiverName);
}
