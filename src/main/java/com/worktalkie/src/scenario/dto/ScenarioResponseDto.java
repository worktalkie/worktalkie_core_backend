package com.worktalkie.src.scenario.dto;

import com.worktalkie.src.scenario.entity.Mission;
import com.worktalkie.src.scenario.entity.Scenario;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class ScenarioResponseDto {

	@Getter
	@Builder
	public static class GetByIdDto {
		private String title;
		private String descriptions;
		private List<String> missions;
		private String tips;
		private int estimatedTime;

		public static GetByIdDto toDto(Scenario scenario, List<Mission> missions) {
			List<String> missionTitles = missions.stream().map(Mission::getTitle).toList();

			return GetByIdDto.builder()
				.title(scenario.getTitle())
				.descriptions(scenario.getDescriptions())
				.missions(missionTitles)
				.tips(scenario.getTips())
				.estimatedTime(scenario.getEstimatedTime())
				.build();
		}
	}

	@Getter
	@Builder
	public static class CreateDto {
		private String title;
		private String descriptions;
		private String backgrounds;
		private String roleOfAi;
		private List<String> missions;
		private String tips;
		private int estimatedTime;
	}

	@Getter
	@Builder
	public static class RecommendDto {
		private String title;
		private String descriptions;
		private int estimatedTime;

		public static RecommendDto toDto(Scenario scenario) {
			return RecommendDto.builder()
				.title(scenario.getTitle())
				.descriptions(scenario.getDescriptions())
				.estimatedTime(scenario.getEstimatedTime())
				.build();
		}
	}
}
