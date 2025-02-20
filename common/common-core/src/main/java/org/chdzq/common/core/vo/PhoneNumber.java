package org.chdzq.common.core.vo;

import lombok.Data;
import lombok.Value;
import org.chdzq.common.core.result.ResultError;
import org.chdzq.common.core.validation.Mobile;
import org.chdzq.common.core.validation.MobileValidator;

/**
 * 电话号码
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 10:07
 */
@Value
public class PhoneNumber {

    /**
     * 电话号码
     */
    String number;

    public PhoneNumber(String number) {
        this.number = number;
    }

    public static PhoneNumber make(String number) {
        if (!MobileValidator.isMobilePattern.matcher(number).matches()) {
            throw ResultError.PARAMETER_ERROR.makeException("电话号码不符合规范");
        }
        return new PhoneNumber(number);
    }

    public static String valueOf(PhoneNumber number) {
        if (number == null) {
            return null;
        }
        return number.getNumber();
    }
}
