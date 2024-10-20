package com.worktalkie.src.conversation.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Builder
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat {

    @Id
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;
    private String message;
    private boolean isAi;
}
