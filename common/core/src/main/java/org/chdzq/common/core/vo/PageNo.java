package org.chdzq.common.core.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * @Description 分页参数
 * @Author chdzq
 * @Date 2024/11/20
 */
public final class PageNo {

    private final Integer page;

    public PageNo(
            @NotNull(message = "页码不能为空")
            @Min(value = 1, message = "页码最小值为 1")
            Integer page) {
        this.page = page;
    }

    public Integer getPage() {
        return page;
    }
}
