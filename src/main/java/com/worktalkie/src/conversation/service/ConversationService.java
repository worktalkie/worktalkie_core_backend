package com.worktalkie.src.conversation.service;

import com.worktalkie.src.conversation.dto.ConversationRequestDto;
import com.worktalkie.src.conversation.dto.ConversationResponseDto;
import com.worktalkie.src.conversation.entity.ChatRoom;
import com.worktalkie.src.conversation.repository.ConversationRepository;
import com.worktalkie.src.global.error.BaseException;
import com.worktalkie.src.global.error.ErrorCode;
import com.worktalkie.src.scenario.ScenarioService;
import com.worktalkie.src.scenario.dto.ScenarioResponseDto;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationService {
    private final ScenarioService scenarioService;
    private final StorageService storageService;

    private final ConversationRepository conversationRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${ml.server}")
    private String ML_SERVER;

    @Transactional
    public ConversationResponseDto.StartDto startConversation(final ConversationRequestDto.CreateDto input, MultipartFile audio) {
        // 시나리오 호출
        ScenarioResponseDto.GetByIdDto scenarioDto = scenarioService.getScenarioById(input.getScenarioId());

        // ML 서버 API 호출
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<ScenarioResponseDto.GetByIdDto> requestEntity = new HttpEntity<>(scenarioDto, headers);

        ResponseEntity<Object> response;
        response = restTemplate.exchange(ML_SERVER, HttpMethod.POST, requestEntity, Object.class);

        Object mlResponse = response.getBody();
        if (mlResponse == null) {
            throw new BaseException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        String chatRoomId = ChatRoom.of(input.getUserId(), input.getScenarioId()).getId();
        conversationRepository.save(ChatRoom.of(input.getUserId(), input.getScenarioId()));

        storageService.storeAudio(chatRoomId, audio);

        return ConversationResponseDto.StartDto.builder()
                .conversationId(chatRoomId)
                .answer(mlResponse.toString())
                .build();
    }

    public ConversationResponseDto.ChatDto chat(final String sessionId, final Object input) {
        //
        return null;
    }

    public ConversationResponseDto.EndDto endConversation(final String sessionId) {
        return new ConversationResponseDto.EndDto();
    }

    public List<ConversationResponseDto.HistoryDto> getChatHistory(final String sessionId) {
        return null;
    }
}
