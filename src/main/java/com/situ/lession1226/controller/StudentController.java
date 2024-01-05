package com.situ.lession1226.controller;

import com.situ.lession1226.model.Student;
import com.situ.lession1226.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2023/12/26
 * <p>
 * created by 千堆雪 on 2023/12/26, last modified by 千堆雪 on 2023/12/26
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    /*@Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }*/

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 处理/list请求，列出学生信息列表
     */
    //@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    @GetMapping("/list")
    public String list(Map<String, Object> map) {
        //List<Student> list = studentService.findAll();
        //map.put("students", list);
        return "student/list";
    }

    /**
     * 处理/student/add请求
     */
    @GetMapping("/add")
    public String add(Map<String, Object> map) {
        map.put("showSubmit", false);//不显示提交按钮
        return "student/add";
    }

    /**
     * 进入修改页面
     */
    @GetMapping("/edit")
    public String edit(Integer id, Map<String, Object> map) {
        Student student = studentService.findById(id);
        if (student == null) {
            map.put("error", "要修改的学生不存在");
        } else {
            map.put("stu", student);
        }
        return "student/edit";
    }
}
