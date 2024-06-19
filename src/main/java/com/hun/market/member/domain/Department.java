package com.hun.market.member.domain;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Embeddable
@Getter
public class Department {
    private String departmentName;
    private String teamName;

    @Builder
    public Department(String departmentName, String teamName) {
        this.departmentName = departmentName;
        this.teamName = teamName;
    }

    public Department() {

    }
}