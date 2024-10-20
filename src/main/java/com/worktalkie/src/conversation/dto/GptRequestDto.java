package com.worktalkie.src.conversation.dto;

import com.worktalkie.src.scenario.entity.Mission;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class GptRequestDto {
    private String scenario;
    private String background;
    private String roleOfAi;
    private List<String> missions;
    private List<Boolean> isMissionCompleted;
    private boolean isEnd;
    private List<String> dialogue;

    public static GptRequestDto createStartConversationDto(String scenario, String background, String roleOfAi, List<Mission> missions) {
        List<String> missionTitles = missions.stream().map(Mission::getTitle).toList();

        return GptRequestDto.builder()
                .scenario(scenario)
                .background(background)
                .roleOfAi(roleOfAi)
                .missions(missionTitles)
                .isMissionCompleted(new ArrayList<>(List.of(false, false, false)))
                .isEnd(false)
                .build();
    }

    public static GptRequestDto createChatDto(String scenario,
                                            String background,
                                            String roleOfAi,
                                            List<Mission> missions,
                                            List<Boolean> isMissionCompleted,
                                            List<String> dialogue) {
        List<String> missionTitles = missions.stream().map(Mission::getTitle).toList();

        return GptRequestDto.builder()
                .scenario(scenario)
                .background(background)
                .roleOfAi(roleOfAi)
                .missions(missionTitles)
                .isMissionCompleted(isMissionCompleted)
                .isEnd(false)
                .dialogue(dialogue)
                .build();
    }

    public static GptRequestDto createEndConversationDto(String scenario,
                                                       String background,
                                                       String roleOfAi,
                                                       List<Mission> missions,
                                                       List<Boolean> isMissionCompleted,
                                                       List<String> dialogue) {
        List<String> missionTitles = missions.stream().map(Mission::getTitle).toList();

        return GptRequestDto.builder()
                .scenario(scenario)
                .background(background)
                .roleOfAi(roleOfAi)
                .missions(missionTitles)
                .isMissionCompleted(isMissionCompleted)
                .isEnd(true)
                .dialogue(dialogue)
                .build();
    }
}
