package com.worktalkie.src.conversation.e2e;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.worktalkie.src.base.IntegrationTest;
import com.worktalkie.src.conversation.dto.ConversationRequest;
import com.worktalkie.src.conversation.dto.ConversationResponse;
import com.worktalkie.src.conversation.repository.ConversationRepository;
import com.worktalkie.src.member.entity.Member;
import com.worktalkie.src.member.repository.MemberRepository;
import com.worktalkie.src.scenario.entity.Mission;
import com.worktalkie.src.scenario.entity.Scenario;
import com.worktalkie.src.scenario.repository.ScenarioRepository;
import com.worktalkie.src.util.JsonUtils;

class ConversationE2ETest extends IntegrationTest {
	@MockBean
	private RestTemplate restTemplate;
	@Autowired
	protected MemberRepository memberRepository;
	@Autowired
	protected ScenarioRepository scenarioRepository;

	@Autowired
	protected ConversationRepository conversationRepository;

	private MockRestServiceServer mockServer;

	@BeforeEach
	void setUp() {
		Member member1 = Member.builder()
			.id("1")
			.email("hyun1234@kakaocorp.com")
			.hashedPassword("hyunhyun123")
			.name("현스톤")
			.build();
		memberRepository.save(member1);

		Scenario inputScenario = Scenario.builder()
			.title("시나리오1")
			.backgrounds("뭔가 배경 있음")
			.descriptions("재밌어보이쥬?")
			.estimatedTime(5)
			.roleOfAi("당신은 AI임다. 잘부탁드립니다요")
			.tips("참고해라. 꼭 해라")
			.build();

		Scenario scenario = scenarioRepository.save(inputScenario);

		Mission mission1 = Mission.builder()
			.title("미션1")
			.isCompleted(false)
			.scenario(scenario)
			.build();
		Mission mission2 = Mission.builder()
			.title("미션2")
			.isCompleted(false)
			.scenario(scenario)
			.build();
		Mission mission3 = Mission.builder()
			.title("미션3")
			.isCompleted(false)
			.scenario(scenario)
			.build();

		scenario.setMissions(List.of(mission1, mission2, mission3));

		mockServer = MockRestServiceServer.createServer(restTemplate);
	}

	@AfterEach
	void cleanUp() {
		mockServer.reset();
	}

	@DisplayName("대화가 정상적으로 시작된다")
	@Test
	void 대화_시작_테스트() throws Exception {
		// given
		ConversationRequest.CreateDto input = ConversationRequest.CreateDto.builder()
			.memberId("1")
			.scenarioId(1L)
			.build();

		// TODO: spy로 처리
		// 외부 호출에 대한 응답 모킹
		mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8000/start"))
			.andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
			.andRespond(MockRestResponseCreators.withSuccess("Mocked Response", MediaType.APPLICATION_JSON));

		ConversationResponse.StartDto mockResponse = new ConversationResponse.StartDto();
		when(restTemplate.postForObject(eq("http://localhost:8000/start"), any(), eq(ConversationResponse.StartDto.class)))
			.thenReturn(mockResponse);

		// when
		final MvcResult result = mockMvc.perform(
				post("/api/conversations")
					.contentType(MediaType.APPLICATION_JSON)
					.content(JsonUtils.toJson(input)))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();

		ConversationResponse.StartDto startDto = JsonUtils.parseResponse(result.getResponse(), new TypeReference<>() {});

		Assertions.assertThat(startDto).hasNoNullFieldsOrProperties();
	}
}