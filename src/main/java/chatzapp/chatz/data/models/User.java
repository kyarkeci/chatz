package chatzapp.chatz.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@Document(collection = "Users")
public class User {

    @Id
    private String id;
    private String email;
    private String password;
    private String userName;
    private  Map<String,Chat> chats;
}
