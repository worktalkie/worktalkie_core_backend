package com.worktalkie.src.member.repository;

import com.worktalkie.src.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
