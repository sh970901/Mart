package com.hun.market.member.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Department {
    private String departmentName;
    private String teamName;
    private String position;
}