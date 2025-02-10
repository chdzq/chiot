package org.chdzq.system.service.impl;

import lombok.AllArgsConstructor;
import org.chdzq.common.core.entity.Page;
import org.chdzq.common.core.tree.TreeUtil;
import org.chdzq.common.mybatis.core.query.WrapperX;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.system.convert.SystemInfraConvertor;
import org.chdzq.system.query.DictionaryItemPageQuery;
import org.chdzq.system.query.DictionaryPageQuery;
import org.chdzq.system.query.model.DictionaryItemVO;
import org.chdzq.system.query.model.DictionaryVO;
import org.chdzq.system.query.model.ResourceTreeVO;
import org.chdzq.system.repository.SystemDictionaryItemRepository;
import org.chdzq.system.repository.dao.SystemDictionaryMapper;
import org.chdzq.system.repository.po.SystemDictionaryDO;
import org.chdzq.system.repository.po.SystemDictionaryItemDO;
import org.chdzq.system.repository.po.SystemResourceDO;
import org.chdzq.system.service.DictionaryQueryService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Dictionary;
import java.util.List;

/**
 * 字典查询服务器类
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 23:42
 */
@Service
@AllArgsConstructor
public class DictionaryQueryServiceImpl extends ServiceImplX<SystemDictionaryMapper, SystemDictionaryDO>
        implements DictionaryQueryService {
    private SystemDictionaryItemRepository dictionaryItemRepository;

    @Override
    public Page<DictionaryVO> page(DictionaryPageQuery param) {
        Page<SystemDictionaryDO> dictionaryPage = customPage(param, (p, q)->
             baseMapper.queryPageList(p, q)
        );
        List<DictionaryVO> voList = SystemInfraConvertor.INSTANCE.dictionaryDOList2DictionaryVOList(dictionaryPage.getList());
        return new Page<>(dictionaryPage.getTotal(), voList);
    }

    @Override
    public Page<DictionaryItemVO> page(DictionaryItemPageQuery param) {
        Page<SystemDictionaryItemDO> page = dictionaryItemRepository.page(param);

        List<DictionaryItemVO> voList = SystemInfraConvertor.INSTANCE.dictionaryItemDOList2DictionaryItemVOList(page.getList());
        return new Page<>(page.getTotal(), voList);
    }
}
