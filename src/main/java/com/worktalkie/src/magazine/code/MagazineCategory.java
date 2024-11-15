package com.worktalkie.src.magazine.code;

public enum MagazineCategory {
	ALL("전체"),
	IT("IT"),
	BUSINESS("비즈니스"),
	LIFE("라이프"),
	ETC("기타");

	private final String category;

	MagazineCategory(String category) {
		this.category = category;
	}

	public static MagazineCategory getCategory(String category) {
		for (MagazineCategory magazineCategory : values()) {
			if (magazineCategory.category.equals(category)) {
				return magazineCategory;
			}
		}
		throw new IllegalArgumentException("Unknown category: " + category);
	}
}
