package com.hun.market.member.domain;

import com.hun.market.base.entity.BaseEntity;
import com.hun.market.item.domain.Item;
import com.hun.market.item.dto.ItemDto;
import com.hun.market.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="member_name", nullable = false, length = 1000)
    private String mbName;

    @Column(name="member_password", nullable = false, length = 1000)
    private String mbPassword;

    @Column(name="member_coin", nullable = false, length = 1000)
    private int mbCoin;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + mbName + '\'' +
                ", email='" + mbPassword + '\'' +
                ", coin='" + mbCoin + '\'' +
                '}';
    }
    public static Member from(MemberDto.MemberCreateRequestDto memberDto){
        return Member.builder()
                .mbName(memberDto.getMbName())
                .mbPassword(memberDto.getMbPassword())
                .mbCoin(memberDto.getMbCoin())
                .build();
    }
}
