package com.worktalkie.src.scenario.service;

import com.worktalkie.src.global.error.BaseException;
import com.worktalkie.src.global.error.ErrorCode;
import com.worktalkie.src.scenario.dto.ScenarioResponseDto;
import com.worktalkie.src.scenario.entity.Mission;
import com.worktalkie.src.scenario.entity.Scenario;
import com.worktalkie.src.scenario.repository.MissionRepository;
import com.worktalkie.src.scenario.repository.ScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScenarioService {
    private final ScenarioRepository scenarioRepository;
    private final MissionRepository missionRepository;

    public ScenarioResponseDto.GetByIdDto getScenarioDtoById(String scenarioId) {
        Scenario scenario = scenarioRepository.findById(scenarioId).orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));

        List<Mission> missions = missionRepository.findAllByScenarioId(scenario.getId());
        List<String> missionTitles = missions.stream().map(Mission::getTitle).toList();

        return ScenarioResponseDto.GetByIdDto.builder()
                .title(scenario.getTitle())
                .descriptions(scenario.getDescriptions())
                .missions(missionTitles)
                .tips(scenario.getTips())
                .estimatedTime(scenario.getEstimatedTime())
                .build();
    }

    public Scenario getScenarioById(String scenarioId) {
        return scenarioRepository.findById(scenarioId).orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
    }

    public List<Mission> getMissionsByScenarioId(String scenarioId) {
        return missionRepository.findAllByScenarioId(scenarioId);
    }

    public List<ScenarioResponseDto.GetByIdDto> getScenarios() {
        List<String> scenarioIds = scenarioRepository.findAll()
                .stream()
                .map(Scenario::getId)
                .toList();

        return scenarioIds.stream()
                .map(this::getScenarioDtoById)
                .toList();
    }
}
