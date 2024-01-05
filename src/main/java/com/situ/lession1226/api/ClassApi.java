package com.situ.lession1226.api;

import com.github.pagehelper.PageInfo;
import com.situ.lession1226.model.ClassEntity;
import com.situ.lession1226.model.Student;
import com.situ.lession1226.service.ClassService;
import com.situ.lession1226.util.PaginateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2024/1/3
 * <p>
 * created by 千堆雪 on 2024/1/3, last modified by 千堆雪 on 2024/1/3
 */
@RestController
@RequestMapping(value = "/api/v1/classes", produces = "application/json;charset=utf-8")
public class ClassApi {
    private ClassService classService;

    @Autowired
    public void setClassService(ClassService classService) {
        this.classService = classService;
    }

    /**
     * 查询所有班级
     */
    @GetMapping
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer pageNo,
                                    @RequestParam(defaultValue = "10") Integer pageSize) {
        //创建一个分页对象
        PaginateInfo pi = PaginateInfo.of(pageNo, pageSize);

        List<ClassEntity> classes = classService.findAll(pi);

        //PageHelper的分页对象
        PageInfo<?> pif = pi.getPageInfo();

        return Map.of("classes", classes, "success", true, "pi", pif);
    }


    /**
     * 查询所有班级，主要是编号和名称
     */
    @GetMapping("/names")
    public List<ClassEntity> findAllClasses() {
        return classService.findAllNames();
    }
}
