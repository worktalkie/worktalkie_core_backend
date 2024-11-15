package com.worktalkie.src.magazine.repository;

import com.worktalkie.src.magazine.code.MagazineCategory;
import com.worktalkie.src.magazine.entity.Magazine;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {

	Slice<Magazine> findAllByDeletedAtIsNull(Pageable pageable);

	Slice<Magazine> findAllByCategoryAndDeletedAtIsNull(MagazineCategory category, Pageable pageable);

	Optional<Magazine> findByIdAndDeletedAtIsNull(Long id);
}
