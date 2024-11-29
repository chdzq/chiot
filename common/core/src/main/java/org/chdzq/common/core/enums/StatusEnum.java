package org.chdzq.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用状态枚举
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 01:25
 */
@AllArgsConstructor
@Getter
public enum StatusEnum implements IBaseEnum<Integer> {
    ENABLE(1, "启用"),
    DISABLE(0, "停用"),
    ;
    private final Integer code;
    private final String name;

    @Override
    public Integer getValue() {
        return code;
    }

    @Override
    public String getLabel() {
        return name;
    }
}
