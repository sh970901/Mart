package com.hun.market.order.claim.repository;

import com.hun.market.order.claim.domain.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByMemberId(Long memberId);
}
