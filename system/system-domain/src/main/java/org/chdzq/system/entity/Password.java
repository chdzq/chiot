package org.chdzq.system.entity;

import lombok.Value;
import org.chdzq.system.adapter.PasswordCoder;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static org.chdzq.common.core.result.ResultError.PARAMETER_ERROR;

/**
 * 密码
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 10:17
 */
@Value
public class Password {

    // 定义正则表达式
    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    // 编译正则表达式
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    String password;

    public Password(String password) {
        this.password = password;
    }


    public static Password make(String password, PasswordCoder passwordCoder) {
        /**
         * 校验密码是否符合规则
         */
        if (null != password && !pattern.matcher(password).matches()) {
            throw PARAMETER_ERROR.makeException("密码不符合规范");
        }
        return new Password(passwordCoder.encode(password));
    }

    public static String valueOf(Password password) {
        if (password == null) {
            return null;
        }
        return password.getPassword();
    }
}
