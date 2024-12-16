package org.chdzq.system.service.impl;

import org.chdzq.system.adapter.PasswordCoder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * TODO
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/13 16:39
 */
@Service
public class PasswordCoderImpl implements InitializingBean, PasswordCoder {
    static Map<String, Supplier<PasswordEncoder>> encoders;

    static  {
        encoders = new HashMap<>();
        encoders.put("bcrypt", BCryptPasswordEncoder::new);
        encoders.put("ldap",  LdapShaPasswordEncoder::new);
        encoders.put("MD4",  Md4PasswordEncoder::new);
        encoders.put("MD5", ()->new MessageDigestPasswordEncoder("MD5"));
        encoders.put("noop", NoOpPasswordEncoder::getInstance);
        encoders.put("pbkdf2", Pbkdf2PasswordEncoder::defaultsForSpringSecurity_v5_5);
        encoders.put("pbkdf2@SpringSecurity_v5_8", Pbkdf2PasswordEncoder::defaultsForSpringSecurity_v5_8);
        encoders.put("scrypt", SCryptPasswordEncoder::defaultsForSpringSecurity_v4_1);
        encoders.put("scrypt@SpringSecurity_v5_8", SCryptPasswordEncoder::defaultsForSpringSecurity_v5_8);
        encoders.put("SHA-1", ()->new MessageDigestPasswordEncoder("SHA-1"));
        encoders.put("SHA-256", ()->new MessageDigestPasswordEncoder("SHA-256"));
        encoders.put("sha256", StandardPasswordEncoder::new);
        encoders.put("argon2", Argon2PasswordEncoder::defaultsForSpringSecurity_v5_2);
        encoders.put("argon2@SpringSecurity_v5_8", Argon2PasswordEncoder::defaultsForSpringSecurity_v5_8);

    }

    private PasswordEncoder passwordEncoder;

    @Value("${chiot.password.encoding-id:bcrypt}")
    private String encodingId;

    @Override
    public void afterPropertiesSet() throws Exception {
        Supplier<PasswordEncoder> supplier = encoders.get(encodingId);
        Assert.notNull(supplier, "请配置正确的加密方式，配置方式:chiot.password.encoding-id=bcrypt");
        passwordEncoder = supplier.get();
    }


    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
