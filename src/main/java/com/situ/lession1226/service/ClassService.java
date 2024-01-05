package com.situ.lession1226.service;

import com.situ.lession1226.model.ClassEntity;
import com.situ.lession1226.util.PaginateInfo;

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
public interface ClassService {
    List<ClassEntity> findAll(PaginateInfo pi);

    /**
     * 查询所有班级名称
     *
     */
    List<ClassEntity> findAllNames();
}
