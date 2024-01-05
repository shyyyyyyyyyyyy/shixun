package com.situ.lession1226.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.lession1226.dao.StudentDAO;
import com.situ.lession1226.model.Student;
import com.situ.lession1226.service.StudentService;
import com.situ.lession1226.util.PaginateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
@Service
public class StudentServiceImpl implements StudentService {
    private StudentDAO dao;

    @Autowired
    public void setDao(StudentDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Student> findAll(PaginateInfo pi, Student search) {
        //自动分页插件的操作
        //1.启动分页
        try (Page<?> p = PageHelper.startPage(pi.getPageNo(), pi.getPageSize());) {
            //2.正常进行查询
            List<Student> students = dao.findAll(search);

            //3.获取分页数据
            PageInfo<Student> pif = new PageInfo<>(students);
            pif.calcByNavigatePages(10);//根据导航页共10页进行计算
            pi.setPageInfo(pif);//将分页对象缓存

            return students;
        }
    }

    @Override
    public Student findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public int deleteByIds(Integer[] ids) {
        return dao.deleteByIds(ids);
    }

    @Override
    public boolean save(Student student) {
        return dao.save(student) > 0;
    }

    @Override
    public boolean update(Student student) {
        return dao.update(student) > 0;
    }
}