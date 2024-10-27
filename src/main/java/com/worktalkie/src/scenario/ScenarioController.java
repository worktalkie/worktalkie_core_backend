package com.worktalkie.src.scenario;

import com.worktalkie.src.scenario.dto.ScenarioResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
