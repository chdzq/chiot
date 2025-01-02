package org.chdzq.system.repository.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chdzq.system.entity.AuthInfo;
import org.chdzq.system.query.UserPageQuery;
import org.chdzq.system.repository.po.SystemUserDO;
import org.chdzq.common.mybatis.core.mapper.BaseMapperX;

/**
 * 用户
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 00:38
 */
@Mapper
public interface SystemUserMapper extends BaseMapperX<SystemUserDO> {

    /**
     * 根据用户名获取认证信息
     * @param username 用户名
     * @return
     */
    AuthInfo selectUserAuthInfo(String username);


    /**
     * 根据查询参数分页查询数据
     * @param page
     * @param param
     * @return
     */
    IPage<SystemUserDO> queryPageList(@Param("page") IPage<SystemUserDO> page, @Param("param") UserPageQuery param);

}
