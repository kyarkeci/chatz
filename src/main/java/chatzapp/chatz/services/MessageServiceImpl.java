package chatzapp.chatz.services;

import chatzapp.chatz.data.models.Message;
import chatzapp.chatz.data.repository.MessageRepository;
import chatzapp.chatz.dtos.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static chatzapp.chatz.utils.Mapper.createMessageMapper;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
   private MessageRepository messageRepository;
    @Override
    public Message createMessage(SendMessageRequest sendMessageRequest){
        Message message = new Message();
        createMessageMapper(message,sendMessageRequest);
        messageRepository.save(message);
        return message;
    }

    @Override
    public void deleteMessage(String chatId) {
        if (findMessage(chatId).isPresent()) {
            Message message = findMessage(chatId).get();
            messageRepository.delete(message);
        }
    }

    @Override
    public Optional<Message> findMessage(String chatId) {
                List<Message> messages = messageRepository.findAll();
        for (Message message :
                messages) {
            if (message.getSender().equals(chatId))
                return Optional.of(message);
        }
        return Optional.empty();
    }


}
