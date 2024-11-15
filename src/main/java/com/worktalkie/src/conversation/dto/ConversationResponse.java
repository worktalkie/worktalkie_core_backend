package com.worktalkie.src.conversation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

public class ConversationResponse {

	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class StartDto {
		private String conversationId;
		private String answer;
	}

	@Getter
	@Builder
	public static class ChatDto {
		private String message;
		private boolean isAi;
		private Timestamp createdAt;
	}

	public static class EndDto {
	}

	public static class HistoryDto {
	}
}