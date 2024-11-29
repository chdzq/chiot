package org.chdzq.common.core.enums;

/**
 * 数据权限枚举
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/27 15:02
 */
public enum DataScopeEnum implements IBaseEnum<Integer> {

    /**
     * value 越小，数据权限范围越大
     */
    ALL(0, "所有数据"),
    DEPT_AND_SUB(1, "部门及子部门数据"),
    DEPT(2, "本部门数据"),
    SELF(3, "本人数据");

    private final Integer code;

    private final String name;

    DataScopeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getValue() {
        return code;
    }

    @Override
    public String getLabel() {
        return name;
    }
}
