package com.bochung.repository;

import com.bochung.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, String> {

    Members findByEmail(String email);
}
