package com.example.account.member.repository;

import com.example.account.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> { //Member :엔티티, Long : PK타입
    Optional<Member> findByUserId(String userId);

}
