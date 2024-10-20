package com.worktalkie.src.conversation.repository;

import com.worktalkie.src.conversation.entity.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {
}
