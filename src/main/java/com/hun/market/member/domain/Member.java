package com.hun.market.member.domain;

import com.hun.market.base.entity.BaseEntity;
import com.hun.market.item.domain.Item;
import com.hun.market.item.dto.ItemDto;
import com.hun.market.member.dto.MemberDto;
import com.hun.market.order.cart.domain.Cart;
import com.hun.market.order.claim.domain.Claim;
import com.hun.market.order.order.domain.Order;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "members")
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

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Claim> claims = new ArrayList<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "departmentName", column = @Column(nullable = false))
    })
    @Nullable
    private Department department;

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
                .department(memberDto.getDepartment())
                .build();
    }
}
