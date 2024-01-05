package com.situ.lession1226.api;

import com.github.pagehelper.PageInfo;
import com.situ.lession1226.model.Student;
import com.situ.lession1226.service.StudentService;
import com.situ.lession1226.util.PaginateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2023/12/27
 * <p>
 * created by 千堆雪 on 2023/12/27, last modified by 千堆雪 on 2023/12/27
 */
@RestController
@RequestMapping(value = "/api/v1/students", produces = "application/json;charset=utf-8")
public class StudentApi {
    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 微服务接口，返回所有学生信息
     *
     */
    @GetMapping
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer pageNo,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    Student student) {
        //创建一个分页对象
        PaginateInfo pi = PaginateInfo.of(pageNo, pageSize);

        List<Student> students = studentService.findAll(pi, student);

        //PageHelper的分页对象
        PageInfo<?> pif = pi.getPageInfo();

        return Map.of("students", students, "success", true, "pi", pif);
    }


    /**
     * 根据学生主键，批量删除
     *
     * @param ids 要批量删除的学生主键
     * @return 成功删除的行数
     */
    @DeleteMapping
    public Map<String, Object> deleteByIds(Integer[] ids) {
        int rows = studentService.deleteByIds(ids);//受影响的行数
        return Map.of("success", true, "rows", rows);
    }

    /**
     * 新增学生
     *
     * @param student
     * @return
     */
    @PostMapping
    public Map<String, Object> save(Student student) {
        boolean success = studentService.save(student);
        return Map.of("success", success);
    }

    /**
     * 修改学生
     *
     * @param student
     * @return
     */
    @PatchMapping
    public Map<String, Object> update(Student student) {
        boolean success = studentService.update(student);
        return Map.of("success", success);
    }
}
