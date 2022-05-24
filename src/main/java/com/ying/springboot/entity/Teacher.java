package com.ying.springboot.entity;

import lombok.Data;

@Data
public class Teacher {
    private String tid;
    private String name;
    private String gender;
    private String title;
    private String introduction;
    private String departmentId;
}
