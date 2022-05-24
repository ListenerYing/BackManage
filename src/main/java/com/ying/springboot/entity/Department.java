package com.ying.springboot.entity;

import lombok.Data;

@Data
public class Department {
    private Integer departmentId;
    private String departmentName;
    private String departmentChairman;
    private String departmentTel;
}
