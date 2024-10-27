package com.worktalkie.src.conversation.entity;

import com.worktalkie.src.global.BaseEntity;
import com.worktalkie.src.member.entity.Member;
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
    @OneToOne(fetch = FetchType.LAZY)
    private Scenario scenario;
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    public static ChatRoom of(Member member, Scenario scenario) {
        return ChatRoom.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .scenario(scenario)
                .member(member)
                .build();
    }
}
