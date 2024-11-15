package com.worktalkie.src.scenario.controller;

import com.worktalkie.src.global.BaseResponse;
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
	public ResponseEntity<BaseResponse<List<ScenarioResponseDto.GetByIdDto>>> getScenarios() {
		return ResponseEntity.ok(new BaseResponse<>(scenarioService.getScenarios()));
	}

	@GetMapping("/{scenarioId}")
	public ResponseEntity<BaseResponse<ScenarioResponseDto.GetByIdDto>> getScenarioById(@PathVariable Long scenarioId) {
		return ResponseEntity.ok(new BaseResponse<>(scenarioService.getScenarioDtoById(scenarioId)));
	}

	@GetMapping("/recommend")
	public ResponseEntity<BaseResponse<List<ScenarioResponseDto.RecommendDto>>> getRecommendScenarios(@RequestParam int count) {
		return ResponseEntity.ok(new BaseResponse<>(scenarioService.getRecommendScenarios(count)));
	}

	@PostMapping("")
	public ResponseEntity<BaseResponse<Object>> createScenario(@RequestBody ScenarioResponseDto.CreateDto input) {
		scenarioService.createScenario(input);
		return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>());
	}

	@DeleteMapping("/{scenarioId}")
	public ResponseEntity<BaseResponse<Object>> deleteScenario(@PathVariable Long scenarioId) {
		scenarioService.deleteScenario(scenarioId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new BaseResponse<>());
	}
}
