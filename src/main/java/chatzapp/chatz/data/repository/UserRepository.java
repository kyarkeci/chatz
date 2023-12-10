package chatzapp.chatz.data.repository;

import chatzapp.chatz.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

}
