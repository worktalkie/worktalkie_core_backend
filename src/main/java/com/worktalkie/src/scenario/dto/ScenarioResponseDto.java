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
}
