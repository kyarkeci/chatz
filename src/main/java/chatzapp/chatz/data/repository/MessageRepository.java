package chatzapp.chatz.data.repository;

import chatzapp.chatz.data.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MessageRepository extends MongoRepository<Message,String> {
    Optional<Message> findBySender(String sender);
}
