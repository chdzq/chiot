package org.chdzq.system.query;

import lombok.Builder;
import lombok.Getter;
import org.chdzq.common.core.ddd.PageQuery;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.validation.InEnum;

import java.time.LocalDateTime;

/**
 * 用户列表查询
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/16 23:58
 */
@Getter
public class UserPageQuery extends PageQuery {

    /**
     *用户名、昵称、手机号关键字
     */
    private final String keyword;

    /**
     * 状态
     */
    private final StatusEnum status;

    /**
     * 开始时间
     */
    private final LocalDateTime startTime;

    /**
     * 结束时间
     */
    private final LocalDateTime endTime;

    @Builder
    public UserPageQuery(Integer pageNo,
                         Integer pageSize,
                         String keyword,
                         @InEnum(StatusEnum.class)
                         Integer status,
                         LocalDateTime startTime,
                         LocalDateTime endTime) {
        super(pageNo, pageSize);
        this.keyword = keyword;
        this.status = StatusEnum.getByCode(status);
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
