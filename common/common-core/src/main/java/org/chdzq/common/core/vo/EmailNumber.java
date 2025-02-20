package org.chdzq.common.core.vo;

import jakarta.validation.constraints.Email;
import lombok.Value;

import java.util.regex.Pattern;

import static org.chdzq.common.core.result.ResultError.PARAMETER_ERROR;

/**
 * 邮箱
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 10:36
 */
@Value
public class EmailNumber {

    public static final Pattern pattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])", 2);


    String email;

    public EmailNumber(String email) {
        this.email = email;
    }

    public static EmailNumber make(String email) {
        if (!pattern.matcher(email).matches()) {
            throw PARAMETER_ERROR.makeException("邮箱符合规范");
        }
        return new EmailNumber(email);
    }

    public static String valueOf(EmailNumber emailNumber) {
        if (emailNumber == null) {
            return null;
        }
        return emailNumber.getEmail();
    }
}
