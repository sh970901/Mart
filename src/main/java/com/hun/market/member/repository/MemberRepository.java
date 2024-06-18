package com.hun.market.member.repository;

import com.hun.market.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMbName(String mbName);

    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.cart WHERE m.mbName = :name")
    Optional<Member> findByMbNameWithCart(@Param("name") String name);

}
