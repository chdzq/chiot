package org.chdzq.system.convert;

import org.chdzq.system.entity.Resource;
import org.chdzq.system.entity.Role;
import org.chdzq.system.entity.User;
import org.chdzq.system.query.model.ResourceTreeVO;
import org.chdzq.system.query.model.RoleVO;
import org.chdzq.system.query.model.UserVO;
import org.chdzq.system.repository.po.SystemResourceDO;
import org.chdzq.system.repository.po.SystemRoleDO;
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

import java.util.List;


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
public interface SystemInfraConvertor {

    SystemInfraConvertor INSTANCE = Mappers.getMapper(SystemInfraConvertor.class);

    @Mappings(
            @Mapping(target = "id", source = "userDO.id")
    )
    User userDo2User(SystemUserDO userDO);

    @Mapping(target = "status", source = "user.status.code")
    @Mapping(target = "gender", source = "user.gender.code")
    SystemUserDO user2UserDo(User user);

    UserVO userDo2UserVO(SystemUserDO userDO);


    @Mapping(target = "enabled", source = "resource.enabled.code")
    @Mapping(target = "type", source = "resource.type.code")
    SystemResourceDO resource2ResourceDO(Resource resource);


    ResourceTreeVO resourceDo2ResourceTreeVO(SystemResourceDO resourceDO);

    List<ResourceTreeVO> resourceDo2ResourceTreeVOList(List<SystemResourceDO> resourceDOList);


    @Mapping(target = "status", source = "role.status.code")
    SystemRoleDO role2RoleDO(Role role);

    RoleVO roleDo2RoleVO(SystemRoleDO roleDO);
}
