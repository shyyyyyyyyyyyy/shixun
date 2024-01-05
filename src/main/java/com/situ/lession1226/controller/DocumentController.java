package com.situ.lession1226.controller;

import com.situ.lession1226.model.Document;
import com.situ.lession1226.service.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/document")
public class DocumentController {

    private DocumentService documentService;

    /*@Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }*/

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * 处理/list请求，列出学生信息列表
     */
    //@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    @GetMapping("/list")
    public String list(Map<String, Object> map) {
        //List<Student> list = studentService.findAll();
        //map.put("students", list);
        return "document/list";
    }

    /**
     * 处理/student/add请求
     */
    @GetMapping("/add")
    public String add(Map<String, Object> map) {
        map.put("showSubmit", false);//不显示提交按钮
        return "document/add";
    }

    /**
     * 进入修改页面
     */
    @GetMapping("/edit")
    public String edit(Integer id, Map<String, Object> map) {
        Document document = documentService.findById(id);
        if (document == null) {
            map.put("error", "要修改的文件不存在");
        } else {
            map.put("doc", document);
        }
        return "document/edit";
    }
}
