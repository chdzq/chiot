package org.chdzq.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 01:30
 */
@AllArgsConstructor
@Getter
public enum GenderEnum implements IBaseEnum<Integer> {

    MALE(1, "男"),
    FEMALE(2, "女"),
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
