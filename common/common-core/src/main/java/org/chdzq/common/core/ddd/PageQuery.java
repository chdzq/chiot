package org.chdzq.common.core.ddd;

import lombok.Value;
import org.chdzq.common.core.vo.PageNo;
import org.chdzq.common.core.vo.PageSize;

/**
 * 分页查询
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/11 22:19
 */
@Value
public class PageQuery implements IQuery {
    /**
     * 页码
     */
    PageNo pageNo;

    /**
     * 每页条数
     */
    PageSize pageSize;
}
