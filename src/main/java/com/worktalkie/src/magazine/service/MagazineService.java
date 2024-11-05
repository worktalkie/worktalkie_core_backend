package com.worktalkie.src.magazine.service;

import com.worktalkie.src.magazine.dto.MagazineResponse;

import java.util.List;

public interface MagazineService {
    List<MagazineResponse.PagingMagazinesDto> getMagazines(String category, int cursor, int limit);

    MagazineResponse.MagazineDetailDto getMagazineDetailById(Long magazineId);

    Long createMagazine(MagazineResponse.CreateMagazineDto input);

    void deleteMagazine(Long magazineId);
}
