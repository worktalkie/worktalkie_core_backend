package com.worktalkie.src.magazine.service;

import com.worktalkie.src.magazine.dto.MagazineResponse;

import java.util.List;

public interface MagazineService {
    List<MagazineResponse.PagingMagazinesDto> getMagazines(String category, int cursor, int limit);
}
