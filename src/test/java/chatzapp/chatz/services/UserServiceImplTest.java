package chatzapp.chatz.services;

import chatzapp.chatz.data.models.Chat;
import chatzapp.chatz.data.models.User;
import chatzapp.chatz.data.repository.ChatRepository;
import chatzapp.chatz.data.repository.MessageRepository;
import chatzapp.chatz.data.repository.UserRepository;
import chatzapp.chatz.dtos.RegisterUserRequest;
import chatzapp.chatz.dtos.SendMessageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @BeforeEach
    public void deleteBeforeEveryTest(){
        userRepository.deleteAll();
        chatRepository.deleteAll();
        messageRepository.deleteAll();
    }
    @Test
    public void testThatUserCanRegister(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUserName("Username");
        registerUserRequest.setPassword("password");

        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));
    }
    @Test
    public void testThatUserCanBeFoundWithEmail(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUserName("Username");
        registerUserRequest.setPassword("password");
        registerUserRequest.setEmail("email");

        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));
        assertThat(userService.findByEmail("email").get().getEmail(),is("email"));
    }

    @Test
    public void testThatUserCanBeFoundWithUserName(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUserName("Username");
        registerUserRequest.setPassword("password");
        registerUserRequest.setEmail("email");

        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));
        assertThat(userService.findByUserName("Username").get().getUserName(),is("Username"));
    }
    @Test
    public void testThatEmailANDUserNameCantNotBeUsedTwice(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUserName("Username");
        registerUserRequest.setPassword("password");
        registerUserRequest.setEmail("email");

        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));
        assertThat(userService.findByEmail("email").get().getEmail(),is("email"));

        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setUserName("Username");
        registerUserRequest1.setPassword("password");
        registerUserRequest1.setEmail("email");

        assertThrows(IllegalArgumentException.class, ()-> userService.register(registerUserRequest1));
        assertThat(userRepository.count(), is(1L));

        RegisterUserRequest registerUserRequest2 = new RegisterUserRequest();
        registerUserRequest2.setUserName("Username2");
        registerUserRequest2.setPassword("password");
        registerUserRequest2.setEmail("email");

        assertThrows(IllegalArgumentException.class, ()-> userService.register(registerUserRequest2));
        assertThat(userRepository.count(), is(1L));

        RegisterUserRequest registerUserRequest3 = new RegisterUserRequest();
        registerUserRequest3.setUserName("Username");
        registerUserRequest3.setPassword("password");
        registerUserRequest3.setEmail("email3");

        assertThrows(IllegalArgumentException.class, ()-> userService.register(registerUserRequest3));
        assertThat(userRepository.count(), is(1L));

        RegisterUserRequest registerUserRequest4 = new RegisterUserRequest();
        registerUserRequest4.setUserName("Username4");
        registerUserRequest4.setPassword("password");
        registerUserRequest4.setEmail("email4");

        userService.register(registerUserRequest4);
        assertThat(userRepository.count(), is(2L));
    }
    @Test
    public void testThatMessagesWork(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUserName("Username");
        registerUserRequest.setPassword("password");
        registerUserRequest.setEmail("email");

        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));
        assertThat(userService.findByEmail("email").get().getEmail(),is("email"));

        assertThat(chatRepository.count(), is(0L));
        assertThat(messageRepository.count(), is(0L));



        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setUserName("Username1");
        registerUserRequest1.setPassword("password1");
        registerUserRequest1.setEmail("email1");
        System.out.println(userService.register(registerUserRequest1));
        assertThat(userRepository.count(), is(2L));
        assertThat(userService.findByEmail("email1").get().getEmail(),is("email1"));
        assertThat(chatRepository.count(), is(0L));
        assertThat(messageRepository.count(), is(0L));

    }
    @Test
    public void testThatMessagesCanBeSent(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUserName("Username");
        registerUserRequest.setPassword("password");
        registerUserRequest.setEmail("email");

        User user = userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));
        assertThat(userService.findByEmail("email").get().getEmail(),is("email"));

        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setUserName("Username1");
        registerUserRequest1.setPassword("password1");
        registerUserRequest1.setEmail("email1");

        User user1 = userService.register(registerUserRequest1);
        assertThat(userRepository.count(), is(2L));
        assertThat(userService.findByEmail("email1").get().getEmail(),is("email1"));

        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.setSenderChatName(registerUserRequest.getUserName());
        sendMessageRequest.setReceiverChatName(registerUserRequest1.getUserName());
        sendMessageRequest.setMessageBody("Guy all glory belongs to God forever and ever and ever");
        userService.sendMessage(sendMessageRequest);
        System.out.println(userRepository);

        SendMessageRequest sendMessageRequest1 = new SendMessageRequest();
        sendMessageRequest1.setSenderChatName(registerUserRequest.getUserName());
        sendMessageRequest1.setReceiverChatName(registerUserRequest1.getUserName());
        sendMessageRequest1.setMessageBody(" Our God is merciful");
        userService.sendMessage(sendMessageRequest1);
        System.out.println(userRepository);

        SendMessageRequest sendMessageRequest2 = new SendMessageRequest();
        sendMessageRequest2.setSenderChatName(registerUserRequest1.getUserName());
        sendMessageRequest2.setReceiverChatName(registerUserRequest.getUserName());
        sendMessageRequest2.setMessageBody("Yes his faithfulness endures forever and ever ");
        userService.sendMessage(sendMessageRequest2);
        System.out.println(userRepository.findAll());
        
    }
}