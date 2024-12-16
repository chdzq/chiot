package org.chdzq.system.adapter;

/**
 * 密码服务
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/13 16:26
 */
public interface PasswordCoder {

    /**
     * 加密
     * @param rawPassword
     * @return
     */
    String encode(String rawPassword);

    /**
     * 匹配密码
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    boolean matches(String rawPassword, String encodedPassword);
}
