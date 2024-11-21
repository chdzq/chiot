package org.chdzq.common.core.entity;

import org.chdzq.common.core.vo.PageNo;
import org.chdzq.common.core.vo.PageSize;

import java.io.Serializable;

public abstract class PageParam implements Serializable {

    /**
     * 页码
     */
    private PageNo pageNo;

    /**
     * 每页条数
     */
    private PageSize pageSize;

    public PageParam(PageNo pageNo, PageSize pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PageNo getPageNo() {
        return pageNo;
    }

    public void setPageNo(PageNo pageNo) {
        this.pageNo = pageNo;
    }

    public PageSize getPageSize() {
        return pageSize;
    }

    public void setPageSize(PageSize pageSize) {
        this.pageSize = pageSize;
    }
}
