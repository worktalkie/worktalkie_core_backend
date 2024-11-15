package com.worktalkie.src.scenario.repository;

import com.worktalkie.src.scenario.entity.Mission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {

	List<Mission> findByScenarioIdAndDeletedAtIsNull(Long scenarioId);

	Optional<Mission> findByIdAndDeletedAtIsNull(Long id);
}
