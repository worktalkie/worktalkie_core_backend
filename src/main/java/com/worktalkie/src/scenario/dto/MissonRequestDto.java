package com.worktalkie.src.scenario.dto;

import lombok.Builder;
import lombok.Getter;

public class MissonRequestDto {

	@Getter
	@Builder
	public static class CreateDto {
		private Long scenarioId;
		private String title;
	}
}
