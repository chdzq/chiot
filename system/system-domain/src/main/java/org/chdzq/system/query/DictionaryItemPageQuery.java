package org.chdzq.system.query;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.chdzq.common.core.ddd.PageQuery;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.validation.InEnum;

/**
 * 字典项分页查询
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 23:33
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class DictionaryItemPageQuery extends PageQuery {
    /**
     * 字典Id
     */
    Long dictionaryId;
    /**
     * 关键字 字典名称或是编码
     */
    String keyword;
    /**
     * 状态(1:正常;0:禁用)
     */
    StatusEnum status;

    @Builder
    public DictionaryItemPageQuery(Integer pageNo,
                                   Integer pageSize,
                                   @NotNull(message = "字典id不能为空")
                                   Long dictionaryId,
                                   String keyword,
                                   @InEnum(StatusEnum.class)
                                   Integer status) {
        super(pageNo, pageSize);
        this.dictionaryId = dictionaryId;
        this.keyword = keyword;
        this.status = StatusEnum.getByCode(status);
    }
}
