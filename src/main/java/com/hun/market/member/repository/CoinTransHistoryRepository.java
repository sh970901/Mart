package com.hun.market.member.repository;

import com.hun.market.member.domain.CoinTransHistory;
import com.hun.market.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CoinTransHistoryRepository extends JpaRepository<CoinTransHistory, Long> {

    List<CoinTransHistory> findByMemberId(Long memberId);

    List<CoinTransHistory> findByMemberIdOrderByEventDateDesc(Long memberId);
}
