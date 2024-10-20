package com.worktalkie.src.conversation.dto;

import lombok.Builder;
import lombok.Getter;

public class ConversationRequestDto {

    @Getter
    @Builder
    public static class CreateDto {
        private String scenarioId;
        private String userId;
    }

    @Getter
    @Builder
    public static class ChatDto {
        private String memberId;
        private String message;
    }

}
