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

    public PageQuery(Integer pageNo, Integer pageSize) {
        this.pageNo = new PageNo(null == pageNo? 1: pageNo);
        this.pageSize = new PageSize(null == pageSize ? 10 : pageSize);
    }
}
