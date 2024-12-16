package org.chdzq.system.adapter;

import lombok.Synchronized;
import org.chdzq.common.core.entity.Tuple2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
    static Map<String, Supplier<PasswordEncoder>> ENCODER_SUPPLIER_MAP;

    static  {
        ENCODER_SUPPLIER_MAP = new HashMap<>();
        ENCODER_SUPPLIER_MAP.put("bcrypt", BCryptPasswordEncoder::new);
        ENCODER_SUPPLIER_MAP.put("ldap",  LdapShaPasswordEncoder::new);
        ENCODER_SUPPLIER_MAP.put("MD4",  Md4PasswordEncoder::new);
        ENCODER_SUPPLIER_MAP.put("MD5", ()->new MessageDigestPasswordEncoder("MD5"));
        ENCODER_SUPPLIER_MAP.put("noop", NoOpPasswordEncoder::getInstance);
        ENCODER_SUPPLIER_MAP.put("pbkdf2", Pbkdf2PasswordEncoder::defaultsForSpringSecurity_v5_5);
        ENCODER_SUPPLIER_MAP.put("pbkdf2@SpringSecurity_v5_8", Pbkdf2PasswordEncoder::defaultsForSpringSecurity_v5_8);
        ENCODER_SUPPLIER_MAP.put("scrypt", SCryptPasswordEncoder::defaultsForSpringSecurity_v4_1);
        ENCODER_SUPPLIER_MAP.put("scrypt@SpringSecurity_v5_8", SCryptPasswordEncoder::defaultsForSpringSecurity_v5_8);
        ENCODER_SUPPLIER_MAP.put("SHA-1", ()->new MessageDigestPasswordEncoder("SHA-1"));
        ENCODER_SUPPLIER_MAP.put("SHA-256", ()->new MessageDigestPasswordEncoder("SHA-256"));
        ENCODER_SUPPLIER_MAP.put("sha256", StandardPasswordEncoder::new);
        ENCODER_SUPPLIER_MAP.put("argon2", Argon2PasswordEncoder::defaultsForSpringSecurity_v5_2);
        ENCODER_SUPPLIER_MAP.put("argon2@SpringSecurity_v5_8", Argon2PasswordEncoder::defaultsForSpringSecurity_v5_8);
    }

    static final Map<String, PasswordEncoder> ENCODER_MAP = new ConcurrentHashMap<>();


    private PasswordEncoder passwordEncoder;

    @Value("${chiot.password.encoding-id:bcrypt}")
    private String encodingId;

    private final String idPrefix = "{";
    private final String idSuffix = "}";


    @Override
    public void afterPropertiesSet() throws Exception {
        String id = this.encodingId;
        Assert.isTrue(ENCODER_SUPPLIER_MAP.containsKey(id), "请配置正确的加密方式，配置方式:chiot.password.encoding-id=bcrypt");
        passwordEncoder = getPasswordEncoderById(id);
    }


    @Override
    public String encode(String rawPassword) {
        return this.idPrefix + this.encodingId + this.idSuffix + this.passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        Tuple2<String, String> result = extractPassword(encodedPassword);
        PasswordEncoder encoder = getPasswordEncoderById(result.getElement1());
        return encoder.matches(rawPassword, encodedPassword);
    }

    private PasswordEncoder getPasswordEncoderById(String id) {
        if (ENCODER_MAP.containsKey(id)) {
            return ENCODER_MAP.get(id);
        }

        synchronized(ENCODER_MAP) {
            if (ENCODER_MAP.containsKey(id)) {
                return ENCODER_MAP.get(id);
            }
            Supplier<PasswordEncoder> supplier = ENCODER_SUPPLIER_MAP.get(id);
            Assert.notNull(supplier, "不支持的加密方式");
            PasswordEncoder encoder = supplier.get();
            ENCODER_MAP.put(id, encoder);
            return encoder;
        }
    }

    private Tuple2<String, String> extractPassword(String prefixEncodedPassword) {
        if (prefixEncodedPassword == null) {
            return Tuple2.of(null, prefixEncodedPassword);
        } else {
            int start = prefixEncodedPassword.indexOf(this.idPrefix);
            if (start != 0) {
                return Tuple2.of(null, prefixEncodedPassword);
            } else {
                int end = prefixEncodedPassword.indexOf(this.idSuffix, start);
                if (end < 0) {
                    return Tuple2.of(null, prefixEncodedPassword);
                }
                String id = prefixEncodedPassword.substring(start + this.idPrefix.length(), end);
                return Tuple2.of(id, prefixEncodedPassword.substring(end + this.idSuffix.length()));
            }
        }
    }
}
