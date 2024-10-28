package com.worktalkie.src.scenario.service;

import com.worktalkie.src.global.error.BaseException;
import com.worktalkie.src.global.error.ErrorCode;
import com.worktalkie.src.scenario.dto.MissonRequestDto;
import com.worktalkie.src.scenario.dto.ScenarioResponseDto;
import com.worktalkie.src.scenario.entity.Mission;
import com.worktalkie.src.scenario.entity.Scenario;
import com.worktalkie.src.scenario.repository.MissionRepository;
import com.worktalkie.src.scenario.repository.ScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScenarioService {
    private final ScenarioRepository scenarioRepository;
    private final MissionRepository missionRepository;

    public ScenarioResponseDto.GetByIdDto getScenarioDtoById(Long scenarioId) {
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

    public Scenario getScenarioById(Long scenarioId) {
        return scenarioRepository.findById(scenarioId).orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
    }

    public List<Mission> getMissionsByScenarioId(Long scenarioId) {
        return missionRepository.findAllByScenarioId(scenarioId);
    }

    public List<ScenarioResponseDto.GetByIdDto> getScenarios() {
        List<Long> scenarioIds = scenarioRepository.findAll()
                .stream()
                .map(Scenario::getId)
                .toList();

        return scenarioIds.stream()
                .map(this::getScenarioDtoById)
                .toList();
    }

    @Transactional
    public Long createScenario(ScenarioResponseDto.CreateDto input) {
        Scenario inputScenario = Scenario.builder()
                .title(input.getTitle())
                .descriptions(input.getDescriptions())
                .backgrounds(input.getBackgrounds())
                .roleOfAi(input.getRoleOfAi())
                .missions(null)
                .tips(input.getTips())
                .estimatedTime(input.getEstimatedTime())
                .build();

        // 미션을 생성한 후에 미션을 시나리오에 추가
        List<Long> missionIds = input.getMissions().stream()
                .map(mission -> {
                    MissonRequestDto.CreateDto missionInput = MissonRequestDto.CreateDto.builder()
                            .scenarioId(inputScenario.getId())
                            .title(mission)
                            .build();
                    return createMission(missionInput);
                })
                .toList();
        List<Mission> missions = missionIds.stream()
                .map(missionId -> missionRepository.findById(missionId)
                        .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND)))
                .toList();
        inputScenario.setMissions(missions);

        return scenarioRepository.save(inputScenario).getId();
    }

    @Transactional
    public Long createMission(MissonRequestDto.CreateDto input) {
        Scenario scenario = getScenarioById(input.getScenarioId());
        Mission mission = Mission.builder()
                .scenario(scenario)
                .title(input.getTitle())
                .build();

        return missionRepository.save(mission).getId();
    }
}
