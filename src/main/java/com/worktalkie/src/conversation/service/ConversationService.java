package com.worktalkie.src.conversation.service;

import com.worktalkie.src.conversation.dto.ConversationRequestDto;
import com.worktalkie.src.conversation.dto.ConversationResponseDto;
import com.worktalkie.src.conversation.dto.GptRequestDto;
import com.worktalkie.src.conversation.entity.Chat;
import com.worktalkie.src.conversation.entity.ChatRoom;
import com.worktalkie.src.conversation.repository.ChatRepository;
import com.worktalkie.src.conversation.repository.ChatRoomRepository;
import com.worktalkie.src.conversation.repository.ConversationRepository;
import com.worktalkie.src.global.code.HeaderType;
import com.worktalkie.src.global.code.HeaderValue;
import com.worktalkie.src.global.error.BaseException;
import com.worktalkie.src.global.error.ErrorCode;
import com.worktalkie.src.scenario.ScenarioService;
import com.worktalkie.src.scenario.dto.ScenarioResponseDto;
import com.worktalkie.src.scenario.entity.Mission;
import com.worktalkie.src.scenario.entity.Scenario;
import com.worktalkie.src.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationService {
    private final ScenarioService scenarioService;
    private final StorageService storageService;

    private final ConversationRepository conversationRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${ml-server.url}")
    private String ML_SERVER;

    // dialog 없음
    @Transactional
    public ConversationResponseDto.StartDto startConversation(final ConversationRequestDto.CreateDto input) {
        // 시나리오 호출
        Scenario scenario = scenarioService.getScenarioById(input.getScenarioId());
        List<Mission> missions = scenarioService.getMissionsByScenarioId(scenario.getId());

        ScenarioResponseDto.GetByIdDto.toDto(scenario, missions);
        GptRequestDto gptInput = GptRequestDto.createStartConversationDto(scenario.getTitle(), scenario.getBackgrounds(), scenario.getRoleOfAi(), missions);

        // ML 서버 API 호출
        HttpHeaders headers = new HttpHeaders();
        headers.set(HeaderType.CONTENT_TYPE.name(), HeaderValue.APPLICATION_JSON.name());
        HttpEntity<GptRequestDto> requestEntity = new HttpEntity<>(gptInput, headers);
        String startConversationUrl = ML_SERVER + "/start";

        ResponseEntity<Object> response = restTemplate.exchange(startConversationUrl, HttpMethod.POST, requestEntity, Object.class);

        Object mlResponse = response.getBody();
        if (mlResponse == null) {
            throw new BaseException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        ChatRoom chatRoom = ChatRoom.of(input.getUserId(), scenario);
        conversationRepository.save(chatRoom);
        String chatRoomId = chatRoom.getId();

        return ConversationResponseDto.StartDto.builder()
                .conversationId(chatRoomId)
                .answer(mlResponse.toString())
                .build();
    }

    @Transactional
    public ConversationResponseDto.ChatDto chat(final String chatRoomId,
                                                final ConversationRequestDto.ChatDto input,
                                                MultipartFile audio) throws IOException {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        Scenario scenario = chatRoom.getScenario();
        List<Mission> missions = scenarioService.getMissionsByScenarioId(scenario.getId());

        ScenarioResponseDto.GetByIdDto scenarioDto = ScenarioResponseDto.GetByIdDto.toDto(scenario, missions);

        // ML 서버 API 호출
        HttpHeaders headers = new HttpHeaders();
        headers.set(HeaderType.CONTENT_TYPE.name(), HeaderValue.APPLICATION_JSON.name());
        HttpEntity<ScenarioResponseDto.GetByIdDto> requestEntity = new HttpEntity<>(scenarioDto, headers);
        String chatUrl = ML_SERVER + "/chat";

        ResponseEntity<Object> response = restTemplate.exchange(chatUrl, HttpMethod.POST, requestEntity, Object.class);
        Object mlResponse = response.getBody();
        if (mlResponse == null) {
            throw new BaseException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        // TODO: 코드 중복 제거
        Chat memberChat = Chat.builder()
                .chatRoom(chatRoom)
                .message(input.getMessage())
                .isAi(false)
                .build();
        chatRepository.save(memberChat);

        Chat gptChat = Chat.builder()
                .chatRoom(chatRoom)
                .message(mlResponse.toString())
                .isAi(true)
                .build();
        chatRepository.save(gptChat);

        storageService.uploadFile(chatRoomId, audio);

        return ConversationResponseDto.ChatDto.builder()
                .message(mlResponse.toString())
                .isAi(true)
                .createdAt(gptChat.getCreatedAt())
                .build();
    }

    public ConversationResponseDto.EndDto endConversation(final String chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        Scenario scenario = chatRoom.getScenario();
        List<Mission> missions = scenarioService.getMissionsByScenarioId(scenario.getId());
        List<Chat> chats = chatRepository.findByChatRoom(chatRoom);

        GptRequestDto gptInput = GptRequestDto.createEndConversationDto(scenario, missions, List.of(false, false, false), chats);
        // ML 서버 API 호출
        HttpHeaders headers = new HttpHeaders();
        headers.set(HeaderType.CONTENT_TYPE.name(), HeaderValue.APPLICATION_JSON.name());
        HttpEntity<GptRequestDto> requestEntity = new HttpEntity<>(gptInput, headers);
        String chatUrl = ML_SERVER + "/endConversation";

        ResponseEntity<Object> response = restTemplate.exchange(chatUrl, HttpMethod.POST, requestEntity, Object.class);

        Object mlResponse = response.getBody();
        if (mlResponse == null) {
            throw new BaseException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        return new ConversationResponseDto.EndDto();
    }

    public List<ConversationResponseDto.HistoryDto> getChatHistory(final String chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
        List<Chat> chats = chatRepository.findByChatRoom(chatRoom);

        return null;
    }
}
