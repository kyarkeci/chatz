package chatzapp.chatz.services;

import chatzapp.chatz.data.models.Chat;
import chatzapp.chatz.data.models.Message;
import chatzapp.chatz.data.repository.ChatRepository;
import chatzapp.chatz.dtos.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static chatzapp.chatz.utils.Mapper.AddMessageMapper;


@Service
public class ChatServiceImpl implements ChatService{
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    MessageServiceImpl messageService;
@Override
    public Chat createChat(SendMessageRequest sendMessageRequest){
       if (chatNameDoesNotExist(sendMessageRequest.getSenderChatName(),sendMessageRequest.getReceiverChatName())){
           Chat chat = new Chat();
           chat.setChatName(sendMessageRequest.getSenderChatName()+sendMessageRequest.getReceiverChatName());
           ArrayList<Message> messages = new ArrayList<>();
           chat.setMessages(messages);
           chatRepository.save(chat);
           return chat;
       }
    return findChat(sendMessageRequest.getSenderChatName(),sendMessageRequest.getReceiverChatName()).get();

    }
    @Override
    public Chat addMessageToChat(SendMessageRequest sendMessageRequest){
        Chat chat = createChat(sendMessageRequest);
        validateChatNames(sendMessageRequest.getSenderChatName(),sendMessageRequest.getReceiverChatName());
        Message message = messageService.createMessage(sendMessageRequest);
        AddMessageMapper(chat,message);
        chatRepository.save(chat);
        return chat;
    }

    @Override
    public Optional<Chat> findChat(String senderChatName, String receiverChatName){

        List<Chat> chats = chatRepository.findAll();
        for (Chat chat : chats) {
            if (chat.getChatName().equals(senderChatName+receiverChatName)) {
                return Optional.of(chat);
            } else if (chat.getChatName().equals(receiverChatName+senderChatName)) {
                return Optional.of(chat);
            }
        }
        return Optional.empty();
    }
    private void validateChatNames(String senderChatName, String receiverChatName){
       if (findChat(senderChatName,receiverChatName).isEmpty())
           throw new IllegalArgumentException("chat name not found");
    }
    private boolean chatNameDoesNotExist(String senderChatName, String receiverChatName){
        return findChat(senderChatName,receiverChatName).isEmpty();
    }
    public List<Chat> allChats(){
    return chatRepository.findAll();
    }


}
