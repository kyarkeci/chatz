package chatzapp.chatz.dtos;

import lombok.Data;

@Data
public class CreateChatRequest {
    private String userOne;
    private String userTwo;
}