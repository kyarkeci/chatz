package chatzapp.chatz.services;

import chatzapp.chatz.data.models.Chat;
import chatzapp.chatz.data.models.User;
import chatzapp.chatz.data.repository.ChatRepository;
import chatzapp.chatz.data.repository.UserRepository;
import chatzapp.chatz.dtos.RegisterUserRequest;
import chatzapp.chatz.dtos.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static chatzapp.chatz.utils.Mapper.registerUserMap;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ChatServiceImpl chatService;

    @Override
    public User register(RegisterUserRequest registerUserRequest) {
        validateUserRegistration(registerUserRequest);
        Map<String,Chat> chats = new HashMap<>();
        User user = new User();
        user.setChats(chats);
        registerUserMap(user,registerUserRequest);
        userRepository.save(user);
        return user;
    }

    private void validateUserRegistration(RegisterUserRequest registerUserRequest){
        if (findByEmail(registerUserRequest.getEmail()).isPresent())
            throw new IllegalArgumentException("Account already exits");
        if (findByUserName(registerUserRequest.getUserName()).isPresent())
            throw new IllegalArgumentException("Account already exits");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> users = userRepository.findAll();
        for (User user :
                users) {
            if (user.getEmail().equals(email))
                return Optional.of(user);
        }
        return Optional.empty();
    }
@Override
    public Optional<User> findByUserName(String userName) {
        List<User> users = userRepository.findAll();
        for (User user :
                users) {
            if (user.getUserName().equals(userName))
                return Optional.of(user);
        }
        return Optional.empty();
    }


    @Override
    public User sendMessage(SendMessageRequest sendMessageRequest) {
        validateMessageSending(sendMessageRequest);
        Chat chat = chatService.addMessageToChat(sendMessageRequest);
        String sentFrom = sendMessageRequest.getSenderChatName();
        String sentTo = sendMessageRequest.getReceiverChatName();
        User user1 = findByUserName(sentFrom).get();
        User user2 = findByUserName(sentTo).get();
        Map<String,Chat> user1Chats = user1.getChats();
        Map<String,Chat> user2Chats = user2.getChats();
        user1Chats.put(sentTo,chat);
        user2Chats.put(sentFrom,chat);
        user1.setChats(user1Chats);
        user2.setChats(user2Chats);
        userRepository.save(user1);
        userRepository.save(user2);
        return user1;
    }
    private void validateMessageSending(SendMessageRequest sendMessageRequest){
        if (findByUserName(sendMessageRequest.getSenderChatName()).isEmpty())
            throw new IllegalArgumentException("contact not found");
        if (findByUserName(sendMessageRequest.getReceiverChatName()).isEmpty())
            throw new IllegalArgumentException("contacts not found");
    }


    @Override
    public Map<String, Chat> viewChat(String senderName, String receiverName){
        if (findByUserName(senderName).isPresent() && findByUserName(receiverName).isPresent()) {
            User user = findByUserName(senderName).get();
            return user.getChats();
        }
        throw new IllegalArgumentException("contact not found");
    }

}
