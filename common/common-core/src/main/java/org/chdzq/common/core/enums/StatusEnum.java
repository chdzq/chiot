package org.chdzq.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.chdzq.common.core.validation.EnumerableValue;

/**
 * 通用状态枚举
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 01:25
 */
@AllArgsConstructor
@Getter
public enum StatusEnum implements IBaseEnum<Integer>, EnumerableValue<Integer> {
    DISABLE(0, "停用"),
    ENABLE(1, "启用"),
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

    public static StatusEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }

    public static Integer valueOf(StatusEnum statusEnum) {
        if (statusEnum == null) {
            return null;
        }
        return statusEnum.getValue();
    }

    @Override
    public Integer getEnumerableValue() {
        return code;
    }
}
