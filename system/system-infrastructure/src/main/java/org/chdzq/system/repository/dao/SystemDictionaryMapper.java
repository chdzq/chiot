package org.chdzq.system.repository.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chdzq.common.mybatis.core.mapper.BaseMapperX;
import org.chdzq.system.query.DictionaryPageQuery;
import org.chdzq.system.repository.po.SystemDictionaryDO;

/**
 * 字典
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 15:57
 */
@Mapper
public interface SystemDictionaryMapper extends BaseMapperX<SystemDictionaryDO> {

    /**
     * 根据查询参数分页查询数据
     * @param page
     * @param param
     * @return
     */
    IPage<SystemDictionaryDO> queryPageList(@Param("page") IPage<SystemDictionaryDO> page,
                                            @Param("param") DictionaryPageQuery param);
}
