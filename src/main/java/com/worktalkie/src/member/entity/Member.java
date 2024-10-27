package com.worktalkie.src.member.entity;

import com.worktalkie.src.global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Builder
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    private String id;
    private String name;
    private String email;
    private String hashedPassword;
}
