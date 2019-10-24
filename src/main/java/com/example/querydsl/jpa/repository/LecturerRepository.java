package com.example.querydsl.jpa.repository;

import com.example.querydsl.jpa.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * 讲师Repository
 * @author Null
 * @date 2019-10-24
 */
public interface LecturerRepository extends
        JpaRepository<Lecturer,Integer>,
        QuerydslPredicateExecutor<Lecturer> {
}
