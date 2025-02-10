package org.chdzq.system.repository.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chdzq.common.mybatis.core.mapper.BaseMapperX;
import org.chdzq.system.query.DictionaryItemPageQuery;
import org.chdzq.system.query.DictionaryPageQuery;
import org.chdzq.system.repository.po.SystemDictionaryDO;
import org.chdzq.system.repository.po.SystemDictionaryItemDO;

/**
 * 字典项
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 15:58
 */
@Mapper
public interface SystemDictionaryItemMapper extends BaseMapperX<SystemDictionaryItemDO> {
    /**
     * 根据查询参数分页查询数据
     * @param page
     * @param param
     * @return
     */
    IPage<SystemDictionaryItemDO> queryPageList(@Param("page") IPage<SystemDictionaryItemDO> page,
                                                @Param("param") DictionaryItemPageQuery param);
}
