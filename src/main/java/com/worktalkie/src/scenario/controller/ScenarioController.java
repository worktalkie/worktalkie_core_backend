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
    public List<ScenarioResponseDto.GetByIdDto> getScenarios() {
        return scenarioService.getScenarios();
    }

    @GetMapping("/{scenarioId}")
    public ScenarioResponseDto.GetByIdDto getScenarioById(@PathVariable Long scenarioId) {
        return scenarioService.getScenarioDtoById(scenarioId);
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
