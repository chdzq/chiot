package org.chdzq.authentication.convert;

import org.chdzq.authentication.entity.User;
import org.chdzq.authentication.repository.po.SystemUserDO;
import org.chdzq.common.core.enums.DataScopeEnum;
import org.chdzq.common.core.enums.GenderEnum;
import org.chdzq.common.core.enums.StatusEnum;
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
@Mapper(uses = {StatusEnum.class, DataScopeEnum.class, GenderEnum.class})
public interface AuthConvertor {

    AuthConvertor INSTANCE = Mappers.getMapper(AuthConvertor.class);

    @Mappings(
            @Mapping(target = "id", source = "userDO.id")
    )
    User userDo2User(SystemUserDO userDO);

}
