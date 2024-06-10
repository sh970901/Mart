package com.hun.market.member.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
public class Department {
    private String departmentName;
    private String teamName;
    private String position;

    @Builder
    public Department(String departmentName, String teamName, String position) {
        this.departmentName = departmentName;
        this.teamName = teamName;
        this.position = position;
    }

    public Department() {

    }
}