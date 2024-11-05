package com.worktalkie.src.magazine.entity;

import com.worktalkie.src.global.BaseEntity;
import com.worktalkie.src.magazine.code.MagazineCategory;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Builder
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Magazine extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private MagazineCategory category;
    private String description;
    // TODO: 자동으로 작성자 생성
    private String createdBy;
}
