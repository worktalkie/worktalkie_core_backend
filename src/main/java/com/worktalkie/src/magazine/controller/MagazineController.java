package com.worktalkie.src.magazine.controller;

import com.worktalkie.src.global.BaseResponse;
import com.worktalkie.src.magazine.dto.MagazineResponse;
import com.worktalkie.src.magazine.service.MagazineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/magazines")
@RestController
@RequiredArgsConstructor
public class MagazineController {
    private final MagazineService magazineService;

    @GetMapping("")
    public ResponseEntity<BaseResponse<List<MagazineResponse.PagingMagazinesDto>>> getMagazines(
            @RequestParam String category,
            @RequestParam int cursor,
            @RequestParam int limit
    ) {
        List<MagazineResponse.PagingMagazinesDto> magazines = magazineService.getMagazines(category, cursor, limit);
        return ResponseEntity.ok(new BaseResponse<>(magazines));
    }

    @GetMapping("/{magazineId}")
    public ResponseEntity<BaseResponse<MagazineResponse.MagazineDetailDto>> getMagazineDetailsById(@PathVariable Long magazineId) {
        MagazineResponse.MagazineDetailDto magazine = magazineService.getMagazineDetailById(magazineId);
        return ResponseEntity.ok(new BaseResponse<>(magazine));
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse<MagazineResponse.MagazineDetailDto>> createMagazine(@RequestBody MagazineResponse.MagazineDetailDto magazine) {
        MagazineResponse.MagazineDetailDto createdMagazine = magazineService.createMagazine(magazine);
        return ResponseEntity.ok(new BaseResponse<>(createdMagazine));
    }


}
