package com.ying.springboot.entity;

import lombok.Data;

@Data
public class Course {
    private Integer cid;
    private String name;
    private Integer credit;
    private String time;
    private String address;
    private String teacher;

}
