package org.chdzq.common.core.ddd;

import lombok.Getter;
import org.chdzq.common.core.vo.PageNo;
import org.chdzq.common.core.vo.PageSize;

/**
 * 分页查询
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/11 22:19
 */
@Getter
public class PageQuery implements IQuery {
    /**
     * 页码
     */
    private final PageNo pageNo;

    /**
     * 每页条数
     */
    private final PageSize pageSize;

    public PageQuery(PageNo pageNo, PageSize pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
