package com.worktalkie.src.magazine.dto;

import com.worktalkie.src.magazine.entity.Magazine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

public class MagazineResponse {
	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PagingMagazinesDto {
		private Long id;
		private String title;
		private String description;
		private Timestamp createdAt;
		private String createdBy;
		private Timestamp updatedAt;

		public static PagingMagazinesDto toDto(Magazine magazine) {
			return PagingMagazinesDto.builder()
				.id(magazine.getId())
				.title(magazine.getTitle())
				.description(magazine.getDescription())
				.createdAt(magazine.getCreatedAt())
				.createdBy(magazine.getCreatedBy())
				.updatedAt(magazine.getUpdatedAt())
				.build();
		}
	}

	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MagazineDetailDto {
		private Long id;
		private String title;
		private String description;
		private Timestamp createdAt;
		private String createdBy;
		private Timestamp updatedAt;

		public static MagazineDetailDto toDto(Magazine magazine) {
			return MagazineDetailDto.builder()
				.id(magazine.getId())
				.title(magazine.getTitle())
				.description(magazine.getDescription())
				.createdAt(magazine.getCreatedAt())
				.createdBy(magazine.getCreatedBy())
				.updatedAt(magazine.getUpdatedAt())
				.build();
		}
	}

	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CreateMagazineDto {
		private String title;
		private String category;
		private String description;
		private String createdBy;
	}
}
