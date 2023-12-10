package chatzapp.chatz.dtos;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SendMessageRequest {

    private String messageBody;
    private String senderChatName;
    private String receiverChatName;


}
