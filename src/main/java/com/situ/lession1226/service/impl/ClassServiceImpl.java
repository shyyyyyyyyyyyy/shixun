package com.situ.lession1226.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.lession1226.dao.ClassDAO;
import com.situ.lession1226.model.ClassEntity;
import com.situ.lession1226.model.Student;
import com.situ.lession1226.service.ClassService;
import com.situ.lession1226.util.PaginateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2024/1/3
 * <p>
 * created by 千堆雪 on 2024/1/3, last modified by 千堆雪 on 2024/1/3
 */
@Service
public class ClassServiceImpl implements ClassService {
    private ClassDAO classDAO;

    @Autowired
    public void setClassDAO(ClassDAO classDAO) {
        this.classDAO = classDAO;
    }

    @Override
    public List<ClassEntity> findAll(PaginateInfo pi) {
        //自动分页插件的操作
        //1.启动分页
        try (Page<?> p = PageHelper.startPage(pi.getPageNo(), pi.getPageSize());) {
            //2.正常进行查询
            List<ClassEntity> classes = classDAO.findAll();

            //3.获取分页数据
            PageInfo<ClassEntity> pif = new PageInfo<>(classes);
            pif.calcByNavigatePages(10);//根据导航页共10页进行计算
            pi.setPageInfo(pif);//将分页对象缓存

            return classes;
        }
    }

    @Override
    public List<ClassEntity> findAllNames() {
        return classDAO.findAllNames();
    }
}
