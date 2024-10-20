package com.worktalkie.src.scenario.repository;

import com.worktalkie.src.scenario.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, String> {

    List<Mission> findAllByScenarioId(String scenarioId);
}
