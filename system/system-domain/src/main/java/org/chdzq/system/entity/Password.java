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

    String encodedPassword;

    public Password(String password, PasswordCoder passwordCoder) {
        this(password, ()->passwordCoder.encode(password), true);
    }

    public Password(String encodedPassword) {
        this(null, ()-> encodedPassword, false);
    }

    public Password(String password, Supplier<String> encodedPasswordSupplier, Boolean check) {

        /**
         * 校验密码是否符合规则
         */
        if (check && null != password && !pattern.matcher(password).matches()) {
            throw PARAMETER_ERROR.makeException("密码不符合规范");
        }
        this.password = password;
        this.encodedPassword = encodedPasswordSupplier.get();
    }

    public static Password of(String encodedPassword) {
        return new Password(encodedPassword);
    }

    public static String valueOf(Password password) {
        if (password == null) {
            return null;
        }
        return password.getEncodedPassword();
    }
}
