package com.worktalkie.src.scenario.entity;

import com.worktalkie.src.global.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.SQLDelete;

@Getter
@Builder
@Entity
@SQLDelete(sql = "UPDATE mission SET deleted_at = NOW() WHERE id = ?")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scenario_id")
	private Scenario scenario;
	private String title;
	private boolean isCompleted;
}
