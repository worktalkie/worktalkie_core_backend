package com.worktalkie.src.conversation.entity;

import com.worktalkie.src.global.BaseEntity;
import com.worktalkie.src.scenario.entity.Scenario;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends BaseEntity {

    @Id
    private String id;
    private String scenarioId;
    private String userId;

    public static ChatRoom of(String userId, String scenarioId) {
        return ChatRoom.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .scenarioId(scenarioId)
                .userId(userId)
                .build();
    }
}
