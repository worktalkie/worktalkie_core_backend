package com.worktalkie.src.conversation.controller;

import com.worktalkie.src.conversation.service.ConversationService;
import com.worktalkie.src.conversation.dto.ConversationRequestDto;
import com.worktalkie.src.conversation.dto.ConversationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConversationController {
    private final ConversationService conversationService;

    @PostMapping("")
    public ResponseEntity<Object> startConversation(@RequestBody ConversationRequestDto.CreateDto input,
                                                    @RequestPart(value = "audio")MultipartFile audio) {
        Object result = conversationService.startConversation(input, audio);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/{sessionId}/message")
    public ResponseEntity<ConversationResponseDto.ChatDto> chat(
            @PathVariable String sessionId,
            @RequestBody ConversationRequestDto.ChatDto input) {
        ConversationResponseDto.ChatDto response = conversationService.chat(sessionId, input);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{sessionId}/end")
    public ResponseEntity<ConversationResponseDto.EndDto> endConversation(@PathVariable String sessionId) {
        ConversationResponseDto.EndDto result = conversationService.endConversation(sessionId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{sessionId}/histories")
    public ResponseEntity<List<ConversationResponseDto.HistoryDto>> getChatHistory(@PathVariable String sessionId) {
        List<ConversationResponseDto.HistoryDto> history = conversationService.getChatHistory(sessionId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
}
