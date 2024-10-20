package com.worktalkie.src.conversation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GptRequestDto {
    private String scenario;
    private String background;
    private String roleOfAi;
    private String[] missions;
    private boolean[] isMissionCompleted;
    private boolean isEnd;
    private String[] dialogue;

    public static GptRequestDto createStartConversationDto(String scenario, String background, String roleOfAi, String[] missions) {
        return GptRequestDto.builder()
                .scenario(scenario)
                .background(background)
                .roleOfAi(roleOfAi)
                .missions(missions)
                .isMissionCompleted(new boolean[]{ false, false, false })
                .isEnd(false)
                .build();
    }

    public static GptRequestDto createChatDto(String scenario,
                                            String background,
                                            String roleOfAi,
                                            String[] missions,
                                            boolean[] isMissionCompleted,
                                            String[] dialogue) {
        return GptRequestDto.builder()
                .scenario(scenario)
                .background(background)
                .roleOfAi(roleOfAi)
                .missions(missions)
                .isMissionCompleted(isMissionCompleted)
                .isEnd(false)
                .dialogue(dialogue)
                .build();
    }

    public static GptRequestDto createEndConversationDto(String scenario,
                                                       String background,
                                                       String roleOfAi,
                                                       String[] missions,
                                                       boolean[] isMissionCompleted,
                                                       String[] dialogue) {
        return GptRequestDto.builder()
                .scenario(scenario)
                .background(background)
                .roleOfAi(roleOfAi)
                .missions(missions)
                .isMissionCompleted(isMissionCompleted)
                .isEnd(true)
                .dialogue(dialogue)
                .build();
    }
}
