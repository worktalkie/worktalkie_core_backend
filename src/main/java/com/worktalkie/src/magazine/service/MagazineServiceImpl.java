package com.worktalkie.src.magazine.service;

import com.worktalkie.src.global.error.BaseException;
import com.worktalkie.src.global.error.ErrorCode;
import com.worktalkie.src.magazine.code.MagazineCategory;
import com.worktalkie.src.magazine.dto.MagazineResponse;
import com.worktalkie.src.magazine.entity.Magazine;
import com.worktalkie.src.magazine.repository.MagazineRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MagazineServiceImpl implements MagazineService {
	private final MagazineRepository magazineRepository;

	@Override
	public List<MagazineResponse.PagingMagazinesDto> getMagazines(String categoryInput, int cursor, int limit) {
		Pageable pageable = PageRequest.of(cursor, limit);
		List<Magazine> magazines = new ArrayList<>();

		if (categoryInput != null) {
			MagazineCategory category = MagazineCategory.getCategory(categoryInput);
			magazines = this.magazineRepository.findAllByCategoryAndDeletedAtIsNull(category, pageable).getContent();
		} else {
			magazines = this.magazineRepository.findAllByDeletedAtIsNull(pageable).getContent();
		}

		return magazines.stream()
			.map(MagazineResponse.PagingMagazinesDto::toDto)
			.toList();
	}

	@Override
	public MagazineResponse.MagazineDetailDto getMagazineDetailById(Long magazineId) {
		Magazine magazine = this.magazineRepository.findByIdAndDeletedAtIsNull(magazineId)
			.orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
		return MagazineResponse.MagazineDetailDto.toDto(magazine);
	}

	@Override
	public Long createMagazine(MagazineResponse.CreateMagazineDto input) {
		Magazine magazine = Magazine.builder()
			.title(input.getTitle())
			.category(MagazineCategory.getCategory(input.getCategory()))
			.description(input.getDescription())
			.build();

		return this.magazineRepository.save(magazine).getId();
	}

	@Override
	public void deleteMagazine(Long magazineId) {
		this.magazineRepository.deleteById(magazineId);
	}
}
