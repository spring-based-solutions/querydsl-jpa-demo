package com.example.querydsl.jpa;

import com.example.querydsl.jpa.entity.Course;
import com.example.querydsl.jpa.entity.Lecturer;
import com.example.querydsl.jpa.entity.QCourse;
import com.example.querydsl.jpa.entity.QLecturer;
import com.example.querydsl.jpa.repository.CourseRepository;
import com.example.querydsl.jpa.repository.LecturerRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @SpringBootTest 默认不支持事务且自动回滚
 * 使用@Transactional 开启事务，
 * 使用@Rollback(false) 关闭自动回滚
 * @author Null
 * @date 2019-10-24
 */
@SpringBootTest
class QuerydslJpaDemoApplicationTests {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    /**
     * 初始化数据
     */
    @BeforeEach
    public void initData(){
        // 清空数据表
        courseRepository.deleteAll();
        lecturerRepository.deleteAll();

        // 初始化讲师
        Lecturer tom=new Lecturer();
        tom.setName("Tom");
        tom.setSex(true);
        lecturerRepository.save(tom);

        Lecturer marry=new Lecturer();
        marry.setName("Marry");
        marry.setSex(false);
        lecturerRepository.save(marry);

        // 初始化课程
        Course chinese=new Course();
        chinese.setName("Chinese");
        chinese.setLecturerId(tom.getId());
        courseRepository.save(chinese);

        Course physics=new Course();
        physics.setName("Physics");
        physics.setLecturerId(tom.getId());
        courseRepository.save(physics);

        Course english=new Course();
        english.setName("English");
        english.setLecturerId(marry.getId());
        courseRepository.save(english);
    }

    /**
     * 根据课程名称模糊查询课程
     */
    @Test
    public void testSelectCourseByNameLike() {
        // 组装查询条件
        QCourse qCourse = QCourse.course;
        // %要自行组装
        BooleanExpression expression = qCourse.name.like("P%");
        System.out.println(courseRepository.findAll(expression));
    }

    /**
     * 根据讲师姓名查课程
     */
    @Test
    public void testSelectCourseByLecturerName(){
        QCourse qCourse = QCourse.course;
        QLecturer qLecturer = QLecturer.lecturer;
        // 这里包含了组装查询条件和执行查询的逻辑，组装好条件后记得执行fetch()
        List<Course> courses=queryFactory.select(qCourse)
                .from(qCourse)
                .leftJoin(qLecturer)
                .on(qCourse.lecturerId.eq(qLecturer.id))
                .where(qLecturer.name.eq("Tom"))
                .fetch();
        System.out.println(courses);
    }

    /**
     * 根据姓名更新讲师性别<br/>
     * 使用@Transactional开启事务<br/>
     * 使用@Rollback(false)关闭自动回滚<br/>
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testUpdateLecturerSexByName(){
        QLecturer qLecturer = QLecturer.lecturer;
        // 更新Tom的性别为女性，返回的是影响记录条数
        long num=queryFactory.update(qLecturer)
                .set(qLecturer.sex,false)
                .where(qLecturer.name.eq("Tom"))
                .execute();
        // 这里输出被更新的记录数
        System.out.println(num);
    }

    /**
     * 根据根据性别删除讲师
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testDeleteLecturerBySex(){
        QLecturer qLecturer = QLecturer.lecturer;
        // 删除性别为男性的讲师
        long num=queryFactory.delete(qLecturer)
                .where(qLecturer.sex.eq(true))
                .execute();
        // 输出被删除的记录数
        System.out.println(num);
    }

}
