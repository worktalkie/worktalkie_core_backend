package com.worktalkie.src.conversation.controller;

import com.worktalkie.src.conversation.service.ConversationService;
import com.worktalkie.src.conversation.dto.ConversationRequest;
import com.worktalkie.src.conversation.dto.ConversationResponse;
import com.worktalkie.src.global.BaseResponse;
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

    @PostMapping("")
    public ResponseEntity<BaseResponse<ConversationResponse.StartDto>> startConversation(@RequestBody ConversationRequest.CreateDto input) {
        ConversationResponse.StartDto result = conversationService.startConversation(input);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponse<>(result));
    }

    @PostMapping("/{chatRoomId}/chat")
    public ResponseEntity<BaseResponse<ConversationResponse.ChatDto>> chat(
            @PathVariable String chatRoomId,
            @RequestBody ConversationRequest.ChatDto input,
            @RequestPart(value = "audio") MultipartFile audio) throws IOException {
        ConversationResponse.ChatDto response = conversationService.chat(chatRoomId, input, audio);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PostMapping("/{chatRoomId}/end")
    public ResponseEntity<BaseResponse<ConversationResponse.EndDto>> endConversation(@PathVariable String chatRoomId) {
        ConversationResponse.EndDto result = conversationService.endConversation(chatRoomId);
        return ResponseEntity.ok(new BaseResponse<>(result));
    }

    @GetMapping("/{chatRoomId}/chat-history")
    public ResponseEntity<BaseResponse<List<ConversationResponse.HistoryDto>>> getChatHistory(@PathVariable String chatRoomId) {
        List<ConversationResponse.HistoryDto> history = conversationService.getChatHistory(chatRoomId);
        return ResponseEntity.ok(new BaseResponse<>(history));
    }
}
