package com.situ.lession1226.service;

import com.situ.lession1226.model.Document;
import com.situ.lession1226.model.Student;
import com.situ.lession1226.util.PaginateInfo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DocumentService {

    List<Document> findAll(PaginateInfo pi, Document search);

    //根据主键查询唯一的一个学生
    Document findById(Integer id);

    int deleteByIds(Integer[] ids);

    boolean save(Document document);

    boolean update(Document document);
}
