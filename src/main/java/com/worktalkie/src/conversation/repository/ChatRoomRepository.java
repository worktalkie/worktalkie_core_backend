package com.worktalkie.src.conversation.repository;

import com.worktalkie.src.conversation.entity.ChatRoom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
}
