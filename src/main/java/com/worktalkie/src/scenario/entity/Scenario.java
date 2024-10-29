package com.worktalkie.src.scenario.entity;

import com.worktalkie.src.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.util.List;

@Getter
@Builder
@Entity
@SQLDelete(sql = "UPDATE scenario SET deleted_at = NOW() WHERE id = ?")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scenario extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String descriptions;
    private String backgrounds;
    private String roleOfAi;

    @Setter
    @OneToMany(mappedBy = "scenario")
    private List<Mission> missions;
    private String tips;
    private int estimatedTime;


}