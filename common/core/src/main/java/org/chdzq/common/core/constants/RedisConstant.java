package org.chdzq.common.core.constants;

/**
 * Redis
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/29 16:32
 */
public interface RedisConstant {
    /**
     * 黑名单TOKEN Key前缀
     */
    String TOKEN_BLACKLIST_PREFIX = "token:black_list:";

    /**
     * 图形验证码key前缀
     */
    String CAPTCHA_CODE_PREFIX = "code:captcha:";

    /**
     * 登录短信验证码key前缀
     */
    String LOGIN_SMS_CODE_PREFIX = "code:sms:login";

    /**
     * 注册短信验证码key前缀
     */
    String REGISTER_SMS_CODE_PREFIX = "code:sms:register";


    /**
     * 角色和权限缓存前缀
     */
    String ROLE_PERMS_PREFIX = "role_perms:";


    /**
     * JWT 密钥对(包含公钥和私钥)
     */
    String JWK_SET_KEY = "jwk:set";
}
