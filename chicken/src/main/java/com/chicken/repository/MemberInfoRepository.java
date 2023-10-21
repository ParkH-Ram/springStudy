package com.chicken.repository;

import com.chicken.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, String> {
    MemberInfo findByMemberId(String memberId);
}
