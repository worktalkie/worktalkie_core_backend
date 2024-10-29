package com.worktalkie.src.scenario.controller;

import com.worktalkie.src.scenario.dto.ScenarioResponseDto;
import com.worktalkie.src.scenario.service.ScenarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/scenarios")
@RestController
@RequiredArgsConstructor
public class ScenarioController {
    private final ScenarioService scenarioService;

    @GetMapping("")
    public ResponseEntity<List<ScenarioResponseDto.GetByIdDto>> getScenarios() {
        return ResponseEntity.ok(scenarioService.getScenarios());
    }

    @GetMapping("/{scenarioId}")
    public ResponseEntity<ScenarioResponseDto.GetByIdDto> getScenarioById(@PathVariable Long scenarioId) {
        return ResponseEntity.ok(scenarioService.getScenarioDtoById(scenarioId));
    }

    @GetMapping("/recommend")
    public ResponseEntity<List<ScenarioResponseDto.RecommendDto>> getRecommendScenarios(@RequestParam int count) {
        return ResponseEntity.ok(scenarioService.getRecommendScenarios(count));
    }

    @PostMapping("")
    public ResponseEntity<Object> createScenario(@RequestBody ScenarioResponseDto.CreateDto input) {
        scenarioService.createScenario(input);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{scenarioId}")
    public ResponseEntity<Object> deleteScenario(@PathVariable Long scenarioId) {
        scenarioService.deleteScenario(scenarioId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
