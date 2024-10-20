package com.worktalkie.src.conversation.entity;

import com.worktalkie.src.global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Builder
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatHistory extends BaseEntity {

        @Id
        private String id;
        private String chatRoomId;
        private String userId;
        private String message;
        private boolean isAi;
}
