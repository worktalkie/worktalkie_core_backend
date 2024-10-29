package com.worktalkie.src.scenario.repository;

import com.worktalkie.src.scenario.entity.Scenario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScenarioRepository extends JpaRepository<Scenario, Long> {

    Optional<Scenario> findByIdAndDeletedAtIsNull(Long id);

    List<Scenario> findByDeletedAtIsNull();

    @Query("SELECT s FROM Scenario s WHERE s.deletedAt IS NULL ORDER BY FUNCTION('RAND')")
    List<Scenario> findRandomScenariosAndDeletedAtIsNull(Pageable pageable);
}
