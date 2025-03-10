package org.chdzq.system.convert;

import org.chdzq.system.entity.*;
import org.chdzq.system.query.model.*;
import org.chdzq.system.repository.po.*;
import org.chdzq.common.core.enums.DataScopeEnum;
import org.chdzq.common.core.enums.GenderEnum;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.vo.EmailNumber;
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

    @Mapping(target = "id", source = "userDO.id")
    @Mapping(target = "department.id", source = "userDO.departmentId")
    @Mapping(target = "password.password", source = "password")
    @Mapping(target = "email.email", source = "email")
    @Mapping(target = "mobile.number", source = "mobile")
    User userDo2User(SystemUserDO userDO);

    @Mapping(target = "status", source = "user.status.code")
    @Mapping(target = "gender", source = "user.gender.code")
    @Mapping(target = "departmentId", source = "department.id")
    SystemUserDO user2UserDo(User user);

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "status", source = "user.status")
    @Mapping(target = "createdTime", source = "user.createdTime")
    @Mapping(target = "departmentId", source = "department.id")
    UserVO userDo2UserVO(SystemUserDO user, SystemDepartmentDO department, List<Long> roles);

    UserInfo userDo2UserInfo(SystemUserDO userDO, List<RoleVO> roles, List<ResourceTreeVO> resources);
    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "status", source = "user.status")
    @Mapping(target = "createdTime", source = "user.createdTime")
    @Mapping(target = "departmentName", source = "department.name")
    UserPageVO userDo2UserPageVO(SystemUserDO user, SystemDepartmentDO department);

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "createdTime", source = "user.createdTime")
    @Mapping(target = "departmentName", source = "department.name")
    UserProfileVO userDo2UserProfileVO(SystemUserDO user, SystemDepartmentDO department, List<String> roleNames);

    @Mapping(target = "enabled", source = "resource.enabled.code")
    @Mapping(target = "type", source = "resource.type.code")
    SystemResourceDO resource2ResourceDO(Resource resource);


    ResourceTreeVO resourceDo2ResourceTreeVO(SystemResourceDO resourceDO);

    List<ResourceTreeVO> resourceDo2ResourceTreeVOList(List<SystemResourceDO> resourceDOList);

    ResourceVO resourceDo2ResourceVO(SystemResourceDO resourceDO);

    List<ResourceVO> resourceDo2ResourceVOList(List<SystemResourceDO> resourceDOList);

    Resource resourceDo2Resource(SystemResourceDO resourceDO);

    List<Resource> resourceDo2ResourceList(List<SystemResourceDO> resourceDOList);


    @Mapping(target = "status", source = "role.status.code")
    SystemRoleDO role2RoleDO(Role role);

    RoleVO roleDo2RoleVO(SystemRoleDO roleDO);

    @Mapping(target = "status", source = "dictionary.status.code")
    SystemDictionaryDO dictionary2SystemDictionaryDO(Dictionary dictionary);
    DictionaryVO dictionaryDO2DictionaryVO(SystemDictionaryDO dictionaryDO);
    List<DictionaryVO> dictionaryDOList2DictionaryVOList(List<SystemDictionaryDO> dictionaryDOList);


    @Mapping(target = "status", source = "item.status.code")
    SystemDictionaryItemDO dictionaryItem2SystemDictionaryItemDO(DictionaryItem item);
    DictionaryItemVO dictionaryItemDO2DictionaryItemVO(SystemDictionaryItemDO itemDO);
    List<DictionaryItemVO> dictionaryItemDOList2DictionaryItemVOList(List<SystemDictionaryItemDO> itemDOList);


    @Mapping(target = "status", source = "department.status.code")
    SystemDepartmentDO department2SystemDepartDO(Department department);
    DepartmentTreeVO departmentDO2SystemDepartTreeVO(SystemDepartmentDO department);
    List<DepartmentTreeVO> departmentDOList2SystemDepartTreeVOList(List<SystemDepartmentDO> departmentList);
    DepartmentVO departmentDO2SystemDepartVO(SystemDepartmentDO department);


}
