package com.worktalkie.src.util;

import java.io.IOException;

import org.springframework.mock.web.MockHttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.worktalkie.src.global.BaseResponse;

public class JsonUtils {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T parseResponse(MockHttpServletResponse response, TypeReference<BaseResponse<T>> typeReference)
		throws IOException {
		BaseResponse<T> baseResponse = objectMapper.readValue(response.getContentAsString(), typeReference);
		return baseResponse.getData();
	}

	public static <T> String toJson(T input) throws JsonProcessingException {
		return objectMapper.writeValueAsString(input);
	}
}
