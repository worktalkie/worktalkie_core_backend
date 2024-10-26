package com.worktalkie.src.conversation.controller;

import com.worktalkie.src.conversation.service.ConversationService;
import com.worktalkie.src.conversation.dto.ConversationRequestDto;
import com.worktalkie.src.conversation.dto.ConversationResponseDto;
import com.worktalkie.src.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/conversations")
@RestController
@RequiredArgsConstructor
public class ConversationController {
    private final ConversationService conversationService;
    private final StorageService storageService;

    @PostMapping("")
    public ResponseEntity<Object> startConversation(@RequestBody ConversationRequestDto.CreateDto input) {
        Object result = conversationService.startConversation(input);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/{chatRoomId}/chat")
    public ResponseEntity<ConversationResponseDto.ChatDto> chat(
            @PathVariable String chatRoomId,
            @RequestBody ConversationRequestDto.ChatDto input,
            @RequestPart(value = "audio") MultipartFile audio) throws IOException {
        ConversationResponseDto.ChatDto response = conversationService.chat(chatRoomId, input, audio);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{chatRoomId}/end")
    public ResponseEntity<ConversationResponseDto.EndDto> endConversation(@PathVariable String chatRoomId) {
        ConversationResponseDto.EndDto result = conversationService.endConversation(chatRoomId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{chatRoomId}/chat-history")
    public ResponseEntity<List<ConversationResponseDto.HistoryDto>> getChatHistory(@PathVariable String chatRoomId) {
        List<ConversationResponseDto.HistoryDto> history = conversationService.getChatHistory(chatRoomId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

//    @GetMapping("/{chatRoomId}/audio-history")
//    public Object getAudioHistory(@PathVariable String chatRoomId) throws IOException {
//        return storageService.getAudioHistory(chatRoomId);
//    }
}
