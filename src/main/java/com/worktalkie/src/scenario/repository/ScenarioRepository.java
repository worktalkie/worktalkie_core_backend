package com.worktalkie.src.scenario.repository;

import com.worktalkie.src.scenario.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScenarioRepository extends JpaRepository<Scenario, Long> {

    Optional<Scenario> findByIdAndDeletedAtIsNull(Long id);

    List<Scenario> findByDeletedAtIsNull();
}
