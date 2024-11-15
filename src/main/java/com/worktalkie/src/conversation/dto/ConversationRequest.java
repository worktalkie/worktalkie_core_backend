package com.worktalkie.src.conversation.dto;

import lombok.Builder;
import lombok.Getter;

public class ConversationRequest {

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
