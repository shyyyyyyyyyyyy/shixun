package com.situ.lession1226.dao;

import com.situ.lession1226.model.Document;
import com.situ.lession1226.model.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DocumentDAO {
    /**
     * 获取所有文件信息的接口
     *
     * @return
     */
    List<Document> findAll(Document document);

    Document findById(Integer id);

    int deleteByIds(Integer[] ids);


    int save(Document document);

    int update(Document document);
}
