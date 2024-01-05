package com.situ.lession1226.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.lession1226.dao.DocumentDAO;
import com.situ.lession1226.dao.StudentDAO;
import com.situ.lession1226.model.Document;
import com.situ.lession1226.model.Student;
import com.situ.lession1226.service.DocumentService;
import com.situ.lession1226.util.PaginateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private DocumentDAO documentDAO;

    @Autowired
    public void setDocumentDAO(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }


    @Override
    public List<Document> findAll(PaginateInfo pi, Document search) {
        //自动分页插件的操作
        //1.启动分页
        try (Page<?> p = PageHelper.startPage(pi.getPageNo(), pi.getPageSize());) {
            //2.正常进行查询
            List<Document> documents = documentDAO.findAll(search);

            //3.获取分页数据
            PageInfo<Document> pif = new PageInfo<>(documents);
            pif.calcByNavigatePages(10);//根据导航页共10页进行计算
            pi.setPageInfo(pif);//将分页对象缓存

            return documents;
        }
    }

    @Override
    public Document findById(Integer id) {
        return documentDAO.findById(id);
    }

    @Override
    public int deleteByIds(Integer[] ids) {
        return documentDAO.deleteByIds(ids);
    }

    @Override
    public boolean save(Document document) {
        return documentDAO.save(document) > 0;
    }

    @Override
    public boolean update(Document document) {
        return documentDAO.update(document) > 0;
    }
}
