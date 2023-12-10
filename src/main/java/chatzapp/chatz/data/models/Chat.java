package chatzapp.chatz.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@Document(collection = "Chats")
public class Chat {

    @Id
    private String id;
    private LocalDate dateCreated;
    private String chatName;
    private ArrayList<Message> messages;

}
