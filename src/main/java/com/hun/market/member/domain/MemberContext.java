package com.hun.market.member.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberContext extends User {

    private String mbName;
    private String mbPassword;
    private int mbCoin;
    private final List<GrantedAuthority> authorities = new ArrayList<>();

    public MemberContext(Member member, List<GrantedAuthority> authorities) {
        super(member.getMbName(), member.getMbPassword(), authorities);
        this.mbCoin = member.getMbCoin();
    }

}
