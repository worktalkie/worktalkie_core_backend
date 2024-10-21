package com.worktalkie.src.conversation.repository;

import com.worktalkie.src.conversation.entity.Chat;
import com.worktalkie.src.conversation.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, String> {

    List<Chat> findByChatRoom(ChatRoom chatRoom);
}
