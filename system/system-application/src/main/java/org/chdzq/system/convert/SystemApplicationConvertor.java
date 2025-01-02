package org.chdzq.system.convert;

import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.system.command.CreateRoleCommand;
import org.chdzq.system.command.UpdateRoleCommand;
import org.chdzq.system.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * 系统bean转换类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/30 00:21
 */
@Mapper(imports = StatusEnum.class)
public interface SystemApplicationConvertor {

    SystemApplicationConvertor INSTANCE = Mappers.getMapper(SystemApplicationConvertor.class);

    Role roleCreateCommand2Dto(CreateRoleCommand command);

    Role roleUpdateCommand2Dto(UpdateRoleCommand command);
}
