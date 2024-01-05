package com.situ.lession1226.dao;

import com.situ.lession1226.model.Student;
import com.situ.lession1226.util.PaginateInfo;
import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface StudentDAO {
    /**
     * 获取所有学生信息的接口
     *
     * @return
     */
    List<Student> findAll(Student search);

    Student findById(Integer id);

    int deleteByIds(Integer[] ids);


    int save(Student student);

    int update(Student student);
}
