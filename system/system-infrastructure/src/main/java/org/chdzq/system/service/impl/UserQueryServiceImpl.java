package org.chdzq.system.service.impl;

import org.chdzq.common.core.entity.Page;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.system.convert.SystemInfraConvertor;
import org.chdzq.system.entity.AuthInfo;
import org.chdzq.system.query.QueryAuthInfo;
import org.chdzq.system.query.UserPageQuery;
import org.chdzq.system.query.model.UserVO;
import org.chdzq.system.repository.dao.SystemUserMapper;
import org.chdzq.system.repository.po.SystemUserDO;
import org.chdzq.system.service.UserQueryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户查询服务
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/17 00:10
 */
@Service
public class UserQueryServiceImpl extends ServiceImplX<SystemUserMapper, SystemUserDO> implements UserQueryService {

    @Override
    public AuthInfo getAuthInfo(QueryAuthInfo param) {
        String username = param.getUsername();
        if (!StringUtils.hasText(username)) {
            return null;
        }
        return baseMapper.selectUserAuthInfo(username);
    }

    @Override
    public Page<? extends UserVO> page(UserPageQuery param) {
        Page<SystemUserDO> page = customPage(param, (p, q) -> baseMapper.queryPageList(p, q));
        return page.map(SystemInfraConvertor.INSTANCE::userDo2UserVO);
    }
}
