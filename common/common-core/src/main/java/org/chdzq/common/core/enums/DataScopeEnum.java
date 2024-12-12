package org.chdzq.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.chdzq.common.core.validation.EnumerableValue;

/**
 * 数据权限枚举
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/27 15:02
 */
@AllArgsConstructor
@Getter
public enum DataScopeEnum implements IBaseEnum<Integer>, EnumerableValue<Integer> {

    /**
     * value 越小，数据权限范围越大
     */
    ALL(0, "所有数据"),
    DEPT_AND_SUB(1, "部门及子部门数据"),
    DEPT(2, "本部门数据"),
    SELF(3, "本人数据");

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

    public static DataScopeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (DataScopeEnum scopeEnum : DataScopeEnum.values()) {
            if (scopeEnum.getCode().equals(code)) {
                return scopeEnum;
            }
        }
        return null;
    }

    public static Integer valueOf(DataScopeEnum dataScopeEnum) {
        if (dataScopeEnum == null) {
            return null;
        }
        return dataScopeEnum.getValue();
    }


    @Override
    public Integer getEnumerableValue() {
        return code;
    }
}
