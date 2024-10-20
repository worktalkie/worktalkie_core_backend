package com.worktalkie.src.conversation.repository;

import com.worktalkie.src.conversation.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
