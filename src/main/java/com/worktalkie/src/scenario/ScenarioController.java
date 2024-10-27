package com.worktalkie.src.scenario;

import com.worktalkie.src.scenario.dto.ScenarioResponseDto;
import lombok.RequiredArgsConstructor;
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
    public ScenarioResponseDto.GetByIdDto getScenarioById(@PathVariable String scenarioId) {
        return scenarioService.getScenarioDtoById(scenarioId);
    }

}
