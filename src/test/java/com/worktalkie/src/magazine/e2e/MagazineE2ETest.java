package com.worktalkie.src.magazine.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.worktalkie.src.base.IntegrationTest;
import com.worktalkie.src.global.BaseResponse;
import com.worktalkie.src.magazine.code.MagazineCategory;
import com.worktalkie.src.magazine.dto.MagazineResponse;
import com.worktalkie.src.magazine.entity.Magazine;
import com.worktalkie.src.magazine.repository.MagazineRepository;
import com.worktalkie.src.util.JsonUtils;

class MagazineE2ETest extends IntegrationTest {

	@Autowired
	MagazineRepository magazineRepository;

	@DisplayName("매거진 목록을 페이징 조회한다")
	@Test
	void 카테고리_매거진_목록_조회_테스트() throws Exception {
		// given
		Magazine magazine1 = Magazine.builder()
			.id(1L)
			.title("매거진1")
			.description("좋은 매거진이다")
			.category(MagazineCategory.IT)
			.createdBy("매니정")
			.build();

		Magazine magazine2 = Magazine.builder()
			.id(2L)
			.title("매거진2")
			.description("좋은 매거진이다")
			.category(MagazineCategory.IT)
			.createdBy("매니저")
			.build();

		Magazine magazine3 = Magazine.builder()
			.id(3L)
			.title("매거진3")
			.description("좋은 매거진이다11")
			.category(MagazineCategory.IT)
			.createdBy("매니정")
			.build();

		magazineRepository.saveAll(List.of(magazine1, magazine2, magazine3));

		// when
		int cursor = 0;
		int limit = 3;
		final MvcResult result = mockMvc.perform(get("/api/magazines?" + "cursor=" + cursor + "&limit=" + limit))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();

		// then
		List<MagazineResponse.PagingMagazinesDto> magazinesDtos = JsonUtils.parseResponse(
			result.getResponse(),
			new TypeReference<>() {}
		);

		Assertions.assertThat(magazinesDtos)
			.hasSize(3)
			.extracting(MagazineResponse.PagingMagazinesDto::getId)
			.containsExactly(1L, 2L, 3L);
	}
}
