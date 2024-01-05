package com.situ.lession1226.service;

import com.situ.lession1226.model.Student;
import com.situ.lession1226.util.PaginateInfo;

import java.util.List;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2023/12/26
 * <p>
 * created by 千堆雪 on 2023/12/26, last modified by 千堆雪 on 2023/12/26
 */
public interface StudentService {
    /**
     * 返回一个学生的集合
     *
     * @return
     */
    List<Student> findAll(PaginateInfo pi, Student search);

    //根据主键查询唯一的一个学生
    Student findById(Integer id);

    int deleteByIds(Integer[] ids);

    boolean save(Student student);

    boolean update(Student student);

}
