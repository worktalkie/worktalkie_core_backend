package com.worktalkie.src.conversation.dto;

import com.worktalkie.src.conversation.entity.Chat;
import com.worktalkie.src.scenario.entity.Mission;
import com.worktalkie.src.scenario.entity.Scenario;

import lombok.AllArgsConstructor;
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
	private List<Object> dialogue;

	public static GptRequestDto createStartConversationDto(String scenario, String background, String roleOfAi,
														   List<Mission> missions
	) {
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
											  List<Boolean> isMissionCompleted
	) {
		List<String> missionTitles = missions.stream().map(Mission::getTitle).toList();

		return GptRequestDto.builder()
			.scenario(scenario)
			.background(background)
			.roleOfAi(roleOfAi)
			.missions(missionTitles)
			.isMissionCompleted(isMissionCompleted)
			.isEnd(false)
			.build();
	}

	public static GptRequestDto createEndConversationDto(Scenario scenario,
														 List<Mission> missions,
														 List<Boolean> isMissionCompleted,
														 List<Chat> chats
	) {
		List<String> missionTitles = missions.stream().map(Mission::getTitle).toList();
		List<Object> dialogues = createDialogueDto(chats);

		return GptRequestDto.builder()
			.scenario(scenario.getTitle())
			.background(scenario.getBackgrounds())
			.roleOfAi(scenario.getRoleOfAi())
			.missions(missionTitles)
			.isMissionCompleted(isMissionCompleted)
			.isEnd(true)
			.dialogue(dialogues)
			.build();
	}

	private static List<Object> createDialogueDto(List<Chat> chats) {
		List<Object> dialogues = new ArrayList<>();
		chats.forEach(chat -> {
			if (chat.isAi()) {
				AiDialogueDto aiDialogueDto = new AiDialogueDto(chat.getMessage());
				aiDialogueDto.AI = chat.getMessage();
				dialogues.add(aiDialogueDto);
			} else {
				UserDialogueDto userDialogueDto = new UserDialogueDto(chat.getMessage());
				userDialogueDto.User = chat.getMessage();
				dialogues.add(userDialogueDto);
			}
		});
		return dialogues;
	}

	@AllArgsConstructor
	private static class AiDialogueDto {
		private String AI;
	}

	@AllArgsConstructor
	private static class UserDialogueDto {
		private String User;
	}
}
