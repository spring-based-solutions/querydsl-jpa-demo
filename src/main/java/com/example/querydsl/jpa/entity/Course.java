package com.example.querydsl.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 课程，一个课程对应一个讲师
 * @author Null
 * @date 2019-10-24
 */
@Data
@Entity
public class Course {
    /**
     * 课程ID
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 对应讲师的ID
     */
    private Long lecturerId;
}
