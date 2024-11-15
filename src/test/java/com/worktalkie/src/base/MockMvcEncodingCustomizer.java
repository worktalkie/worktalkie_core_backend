package com.worktalkie.src.base;

import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;

@Component
public class MockMvcEncodingCustomizer implements MockMvcBuilderCustomizer {
	@Override
	public void customize(ConfigurableMockMvcBuilder<?> builder) {
		builder.alwaysDo(result -> result.getResponse().setCharacterEncoding("UTF-8"));
	}
}
