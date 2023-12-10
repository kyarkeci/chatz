package chatzapp.chatz.utils;

import chatzapp.chatz.data.models.Chat;
import chatzapp.chatz.data.models.Message;
import chatzapp.chatz.data.models.User;
import chatzapp.chatz.dtos.RegisterUserRequest;
import chatzapp.chatz.dtos.SendMessageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static void registerUserMap(User user, RegisterUserRequest registerUserRequest){
        user.setUserName(registerUserRequest.getUserName());
        user.setEmail(registerUserRequest.getEmail());
        user.setPassword(registerUserRequest.getPassword());
    }
    public static String  timeAndDateOfMessage(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }
    public static void createMessageMapper(Message message, SendMessageRequest sendMessageRequest){
        message.setBody(sendMessageRequest.getMessageBody() + " " + timeAndDateOfMessage());
        message.setSender(sendMessageRequest.getSenderChatName());
        message.setDateCreated(LocalDate.now());
    }
    public static void AddMessageMapper(Chat chat, Message message){
        ArrayList<Message> messages = chat.getMessages();
        messages.add(message);
        chat.setMessages(messages);
    }
}
