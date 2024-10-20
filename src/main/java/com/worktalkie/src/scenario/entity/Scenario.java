package com.worktalkie.src.scenario.entity;

import com.worktalkie.src.global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scenario extends BaseEntity {

    @Id
    private String id;
    private String title;
    private String descriptions;
    private String backgrounds;
    private String roleOfAi;
    @OneToMany(mappedBy = "scenario")
    private List<Mission> missions;
    private String tips;
    private int estimatedTime;
}
