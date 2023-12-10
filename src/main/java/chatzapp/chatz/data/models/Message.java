package chatzapp.chatz.data.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "Message")
public class Message {

    @Id
    private String id;
    @CreatedDate
    private LocalDate dateCreated;
    private String body;
    private boolean isRead;
    private String sender;
}
