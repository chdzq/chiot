package org.chdzq.system.convert;

import org.chdzq.system.entity.User;
import org.chdzq.system.repository.po.SystemUserDO;
import org.chdzq.common.core.enums.DataScopeEnum;
import org.chdzq.common.core.enums.GenderEnum;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.vo.EmailNumber;
import org.chdzq.system.entity.Password;
import org.chdzq.common.core.vo.PhoneNumber;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


/**
 * 系统bean转换类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/30 00:21
 */
@Mapper(uses = {
        StatusEnum.class,
        DataScopeEnum.class,
        GenderEnum.class,
        Password.class,
        EmailNumber.class,
        PhoneNumber.class,}
)
public interface AuthConvertor {

    AuthConvertor INSTANCE = Mappers.getMapper(AuthConvertor.class);

    @Mappings(
            @Mapping(target = "id", source = "userDO.id")
    )
    User userDo2User(SystemUserDO userDO);

    @Mapping(target = "status", source = "user.status.code")
    @Mapping(target = "gender", source = "user.gender.code")
    SystemUserDO user2UserDo(User user);
}
