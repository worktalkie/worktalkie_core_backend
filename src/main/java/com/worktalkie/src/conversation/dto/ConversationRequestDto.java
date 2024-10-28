package com.worktalkie.src.conversation.dto;

import lombok.Builder;
import lombok.Getter;

public class ConversationRequestDto {

    @Getter
    @Builder
    public static class CreateDto {
        private Long scenarioId;
        private String memberId;
    }

    @Getter
    @Builder
    public static class ChatDto {
        private String memberId;
        private String message;
    }

}
