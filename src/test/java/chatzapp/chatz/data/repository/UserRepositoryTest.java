package chatzapp.chatz.data.repository;

import chatzapp.chatz.data.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
@DataMongoTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    public  void testThatSaveWorks(){
       User user = new User();
       user.setUserName("victor");
       user.setPassword("password");
       userRepository.deleteAll();
        assertThat(userRepository.count(), is(0L));

    }

}