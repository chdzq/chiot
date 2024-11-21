package org.chdzq.common.core.vo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * @Description 分页 值对象
 * @Author chdzq
 * @Date 2024/11/20
 */
public final class PageSize {
    /**
     * 每页条数
     */
    private final Integer size;

    public PageSize(
            @NotNull(message = "每页条数不能为空")
            @Min(value = 1, message = "每页条数最小值为 1")
            @Max(value = 100, message = "每页条数最大值为 100")
            Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }
}
