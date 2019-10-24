package com.example.querydsl.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 讲师，一个讲师有多个课程
 * @author Null
 * @date 2019-10-24
 */
@Data
@Entity
public class Lecturer {
    /**
     * 讲师ID
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /**
     * 讲师名字
     */
    private String name;
    /**
     * 性别，true（1）为男性，false（0）为女性
     */
    private Boolean sex;
}
