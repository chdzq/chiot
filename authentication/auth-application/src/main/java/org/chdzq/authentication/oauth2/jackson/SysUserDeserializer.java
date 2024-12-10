package org.chdzq.authentication.oauth2.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.chdzq.authentication.entity.SysUserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;
import java.util.Set;

/**
 * @see org.springframework.security.jackson2.UserDeserializer
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/9 22:18
 */
public class SysUserDeserializer extends JsonDeserializer<SysUserDetail> {

    private static final TypeReference<Set<SimpleGrantedAuthority>> SIMPLE_GRANTED_AUTHORITY_SET = new TypeReference<Set<SimpleGrantedAuthority>>() {};
    private static final TypeReference<Set<String>> PERMISSIONS_SET = new TypeReference<Set<String>>() {};

    /**
     * This method will create {@link User} object. It will ensure successful object
     * creation even if password key is null in serialized json, because credentials may
     * be removed from the {@link User} by invoking {@link User#eraseCredentials()}. In
     * that case there won't be any password key in serialized json.
     *
     * @param jp   the JsonParser
     * @param ctxt the DeserializationContext
     * @return the user
     * @throws IOException if a exception during IO occurs
     */
    @Override
    public SysUserDetail deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        JsonNode jsonNode = mapper.readTree(jp);
        Set<? extends GrantedAuthority> authorities = mapper.convertValue(jsonNode.get("authorities"),
                SIMPLE_GRANTED_AUTHORITY_SET);
        JsonNode passwordNode = readJsonNode(jsonNode, "password");
        Long userId = readJsonNode(jsonNode, "userId").asLong();
        String username = readJsonNode(jsonNode, "username").asText();
        String password = passwordNode.asText("");
        Integer dataScope = readJsonNode(jsonNode, "dataScope").asInt();
        Long deptId = readJsonNode(jsonNode, "deptId").asLong();
        boolean enabled = readJsonNode(jsonNode, "enabled").asBoolean();
        boolean accountNonExpired = readJsonNode(jsonNode, "accountNonExpired").asBoolean();
        boolean credentialsNonExpired = readJsonNode(jsonNode, "credentialsNonExpired").asBoolean();
        boolean accountNonLocked = readJsonNode(jsonNode, "accountNonLocked").asBoolean();
        Set<String> permissions = mapper.convertValue(jsonNode.get("permissions"),
                PERMISSIONS_SET);

        return SysUserDetail.builder()
                .userId(userId)
                .dataScope(dataScope)
                .username(username)
                .password(password)
                .deptId(deptId)
                .authorities(authorities)
                .permissions(permissions)
                .enabled(enabled)
                .accountNonExpired(accountNonExpired)
                .accountNonLocked(accountNonLocked)
                .credentialsNonExpired(credentialsNonExpired)
                .build();
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field) {
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }
}
