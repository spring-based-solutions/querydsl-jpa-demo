package com.example.querydsl.jpa.repository;

import com.example.querydsl.jpa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * 课程Repository
 *
 * @author Null
 * @date 2019-10-24
 */
public interface CourseRepository extends
        JpaRepository<Course, Integer>,
        QuerydslPredicateExecutor<Course> {
}
