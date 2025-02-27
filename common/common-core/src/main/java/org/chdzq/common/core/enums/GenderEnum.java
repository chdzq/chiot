package org.chdzq.common.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.chdzq.common.core.dictionary.DictionaryConstantInterface;
import org.chdzq.common.core.validation.EnumerableValue;

/**
 * 性别
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 01:30
 */
@AllArgsConstructor
@Getter
public enum GenderEnum implements IBaseEnum<Integer>, EnumerableValue<Integer>, DictionaryConstantInterface<Integer>, IEnum<Integer> {

    MALE(1, "男"),
    FEMALE(2, "女"),
    ;

    @EnumValue
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

    public static GenderEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (GenderEnum genderEnum : GenderEnum.values()) {
            if (genderEnum.getCode().equals(code)) {
                return genderEnum;
            }
        }
        return null;
    }

    public static Integer valueOf(GenderEnum genderEnum) {
        if (genderEnum == null) {
            return null;
        }
        return genderEnum.getValue();
    }

    @Override
    public Integer getEnumerableValue() {
        return code;
    }

    @Override
    public Integer getDictionaryKey() {
        return code;
    }

    @Override
    public String getDictionaryValue() {
        return name;
    }
}
