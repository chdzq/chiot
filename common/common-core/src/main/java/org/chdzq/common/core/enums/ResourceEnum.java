package org.chdzq.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.chdzq.common.core.validation.EnumerableValue;

/**
 * 资源类型
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/31 11:00
 */
@AllArgsConstructor
@Getter
public enum ResourceEnum  implements IBaseEnum<Integer>, EnumerableValue<Integer> {
    PAGE(1, "菜单"),
    DIRECTORY(2, "目录"),
    LINK(3, "外链"),
    BUTTON(4, "按钮")
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

    public static ResourceEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (ResourceEnum v : ResourceEnum.values()) {
            if (v.getCode().equals(code)) {
                return v;
            }
        }
        return null;
    }

    public static Integer valueOf(GenderEnum v) {
        if (v == null) {
            return null;
        }
        return v.getValue();
    }

    @Override
    public Integer getEnumerableValue() {
        return code;
    }
}
