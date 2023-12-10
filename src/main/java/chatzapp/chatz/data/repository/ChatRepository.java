package chatzapp.chatz.data.repository;

import chatzapp.chatz.data.models.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat,String> {
}
