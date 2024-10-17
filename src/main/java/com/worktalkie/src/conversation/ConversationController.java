package com.worktalkie.src.conversation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConversationController {
    private final ConversationService conversationService;

    @PostMapping("")
    public ResponseEntity<Object> startConversation(@RequestBody Object input) {
        Object result = conversationService.startConversation(input);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/{sessionId}/message")
    public ResponseEntity<Object> chat(
            @PathVariable String sessionId,
            @RequestBody Object input) {
        Object response = conversationService.chat(sessionId, input);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param sessionId
     * @summary 채팅 종료 API
     * @tag conversation
     */
    @PostMapping("/{sessionId}/end")
    public ResponseEntity<EndResponseDto> endConversation(@PathVariable String sessionId) {
        EndResponseDto result = conversationService.endConversation(sessionId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * @param sessionId
     * @summary 채팅 기록 조회 API
     * @tag conversation
     */
    @GetMapping("/{sessionId}/histories")
    public ResponseEntity<List<ChatHistoryDto>> getChatHistory(@PathVariable String sessionId) {
        List<ChatHistoryDto> history = conversationService.getChatHistory(sessionId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
}
