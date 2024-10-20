package com.worktalkie.src.conversation.dto;

import lombok.Builder;
import lombok.Getter;

public class ConversationResponseDto {

    @Getter
    @Builder
    public class StartDto {
        private String conversationId;
        private String answer;
    }

    public static class ChatDto {

    }

    public static class EndDto {
    }

    public static class HistoryDto {
    }
}