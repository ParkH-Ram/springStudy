package org.study.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.study.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
    
}
