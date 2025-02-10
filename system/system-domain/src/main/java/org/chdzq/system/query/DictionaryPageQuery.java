package org.chdzq.system.query;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import org.chdzq.common.core.ddd.PageQuery;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.validation.InEnum;

/**
 * 字典分页查询
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 23:33
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class DictionaryPageQuery extends PageQuery {
    /**
     * 关键字 字典名称或是编码
     */
    String keyword;

    /**
     * 状态(1:正常;0:禁用)
     */
    StatusEnum status;

    @Builder
    public DictionaryPageQuery(Integer pageNo,
                               Integer pageSize,
                               String keyword,
                               @InEnum(StatusEnum.class)
                               Integer status) {
        super(pageNo, pageSize);
        this.keyword = keyword;
        this.status = StatusEnum.getByCode(status);
    }
}
