package com.situ.lession1226.api;

import com.github.pagehelper.PageInfo;
import com.situ.lession1226.model.Document;
import com.situ.lession1226.model.Student;
import com.situ.lession1226.service.DocumentService;
import com.situ.lession1226.service.StudentService;
import com.situ.lession1226.util.PaginateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/documents", produces = "application/json;charset=utf-8")
public class DocumentApi {
    private DocumentService documentService;


    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }



    /**
     * 微服务接口，返回所有学生信息
     *
     */
    @GetMapping
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer pageNo,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    Document document) {
        //创建一个分页对象
        PaginateInfo pi = PaginateInfo.of(pageNo, pageSize);

        List<Document> documents = documentService.findAll(pi, document);

        //PageHelper的分页对象
        PageInfo<?> pif = pi.getPageInfo();

        return Map.of("students", documents, "success", true, "pi", pif);
    }


    /**
     * 根据学生主键，批量删除
     *
     * @param ids 要批量删除的学生主键
     * @return 成功删除的行数
     */
    @DeleteMapping
    public Map<String, Object> deleteByIds(Integer[] ids) {
        int rows = documentService.deleteByIds(ids);//受影响的行数
        System.out.println("ids" + rows);
        return Map.of("success", true, "rows", rows);
    }

    /**
     * 新增学生
     *
     * @param student
     * @return
     */
    @PostMapping
    public Map<String, Object> save(Document document) {
        boolean success = documentService.save(document);
        return Map.of("success", success);
    }

    /**
     * 修改学生
     *
     * @par
     * @return
     */
    @PatchMapping
    public Map<String, Object> update(Document document) {
        boolean success = documentService.update(document);
        return Map.of("success", success);
    }
}
