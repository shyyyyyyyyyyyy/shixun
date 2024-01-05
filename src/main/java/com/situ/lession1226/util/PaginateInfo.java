package com.situ.lession1226.util;

import com.github.pagehelper.PageInfo;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2023/12/27
 * <p>
 * created by 千堆雪 on 2023/12/27, last modified by 千堆雪 on 2023/12/27
 */
public class PaginateInfo {
    private final int pageNo;//页码
    private final int pageSize;//页面大小

    private PageInfo<?> pageInfo;//PageHelper的分页对象

    private PaginateInfo(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public static PaginateInfo of(int pageNo, int pageSize) {
        return new PaginateInfo(pageNo, pageSize);
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    //查询的起始位置
    public int getOffset() {
        return (this.pageNo - 1) * this.pageSize;
    }

    //查询的记录数
    public int getLimit() {
        return this.getPageSize();
    }


    public PageInfo<?> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<?> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
