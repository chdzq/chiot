package org.chdzq.common.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InEnumValidator implements ConstraintValidator<InEnum, Object> {

    private List<Object> values;

    @Override
    public void initialize(InEnum annotation) {
        Enum<? extends EnumerableValue>[] instances = annotation.value().getEnumConstants();
        if (instances.length == 0) {
            this.values = Collections.emptyList();
        } else {
            List<Object> values = Arrays.stream(instances)
                    .map(($0)-> ((EnumerableValue)$0).getEnumerableValue())
                    .collect(Collectors.toList());
            List<Integer> excludedIndex = Arrays.stream(annotation.excludedIndex()).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            int size = values.size();
            if (!CollectionUtils.isEmpty(excludedIndex)) {
                for (Integer $0: excludedIndex) {
                    if ($0.intValue() >= size) {
                        continue;
                    }
                    values.remove($0.intValue());
                }
            }
            this.values = values;
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // 为空时，且不校验，即认为通过
        // 校验通过
        if ( value == null || values.contains(value)) {
            return true;
        }
        // 校验不通过，自定义提示语句（因为，注解上的 value 是枚举类，无法获得枚举类的实际值）
        context.disableDefaultConstraintViolation(); // 禁用默认的 message 的值
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()
                .replaceAll("\\{value}", values.toString())).addConstraintViolation(); // 重新添加错误提示语句
        return false;
    }

}

