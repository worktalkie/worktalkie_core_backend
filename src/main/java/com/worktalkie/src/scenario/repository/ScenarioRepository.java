package com.worktalkie.src.scenario.repository;

import com.worktalkie.src.scenario.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioRepository extends JpaRepository<Scenario, Long> {
}
