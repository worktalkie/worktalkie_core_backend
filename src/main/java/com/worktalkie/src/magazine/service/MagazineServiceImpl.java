package com.worktalkie.src.magazine.service;

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
}
