package com.worktalkie.src.magazine.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MagazineCategoryTest {

	@Test
	void 입력을_통해_카테고리를_정상적으로_가져온다() {
		assertEquals(MagazineCategory.ALL, MagazineCategory.getCategory("전체"));
		assertEquals(MagazineCategory.IT, MagazineCategory.getCategory("IT"));
		assertEquals(MagazineCategory.BUSINESS, MagazineCategory.getCategory("비즈니스"));
		assertEquals(MagazineCategory.LIFE, MagazineCategory.getCategory("라이프"));
		assertEquals(MagazineCategory.ETC, MagazineCategory.getCategory("기타"));
	}

	@Test
	void getCategoryException() {
		assertThrows(IllegalArgumentException.class, () -> MagazineCategory.getCategory("Unknown"));
	}

}