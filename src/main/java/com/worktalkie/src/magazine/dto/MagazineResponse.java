package com.worktalkie.src.magazine.dto;

import com.worktalkie.src.magazine.entity.Magazine;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

public class MagazineResponse {

    @Getter
    @Builder
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
}
