package org.chdzq.common.core.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 校验工具
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/3 00:42
 */
public class ValidationUtil {

    private ValidationUtil() {}

    private static final Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws IllegalArgumentException 校验不通过，则报业务异常
     */
    public static void validate(Object object, Class<?>... groups) throws IllegalArgumentException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            String msg = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("||"));
            throw new IllegalArgumentException(msg);
        }
    }
}