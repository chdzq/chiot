package org.chdzq.system.convert;

import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.system.entity.Role;
import org.chdzq.system.entity.User;
import org.chdzq.system.entity.UserAuthInfo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 系统bean转换类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/30 00:21
 */
@Mapper(imports = StatusEnum.class)
public interface SystemConvert {

    SystemConvert INSTANCE = Mappers.getMapper(SystemConvert.class);


    @Mapping(target = "userId", source = "id")
    UserAuthInfo userConvert2AuthInfo(User user);

    default String getRoleCode(Role role) {
        return role.getCode();
    }

    @AfterMapping
    static void userAfterConvert2AuthInfo(User user, @MappingTarget UserAuthInfo info) {
        List<Role> roles = user.getRoles();
        if (CollectionUtils.isEmpty(roles)) {
            return;
        }
        roles.stream()
                .max((o1, o2) -> o2.getDataScope().getValue().compareTo(o1.getDataScope().getValue()))
                .ifPresent((a)->{
                    user.setDataScope(a.getDataScope());
                });

    }

}
