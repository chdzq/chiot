package org.chdzq.common.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class MobileValidator implements ConstraintValidator<Mobile, String> {

    public static final Pattern isMobilePattern = Pattern.compile("(?:0|86|\\+86)?1[3-9]\\d{9}");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 如果手机号为空，默认不校验，即校验通过
        if (!StringUtils.hasText(value)) {
            return true;
        }
        // 校验手机
        return isMobilePattern.matcher(value).matches();
    }

}
