package org.chdzq.system.repository.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chdzq.common.mybatis.core.mapper.BaseMapperX;
import org.chdzq.system.query.ResourcePageQuery;
import org.chdzq.system.repository.po.SystemResourceDO;

/**
 * 资源
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 00:42
 */
@Mapper
public interface SystemResourceMapper extends BaseMapperX<SystemResourceDO> {

    /**
     * 根据编码和父节点查询主键
     * @param parentId 父节点
     * @param code 编码
     * @return 主键
     */
    Long selectResourceIdByCode(@Param("parentId")Long parentId,
                                @Param("code")String code);


    /**
     * 根据名称和父节点查询主键
     * @param parentId 父节点
     * @param name 名称
     * @return 主键
     */
    Long selectResourceIdByName(@Param("parentId")Long parentId,
                                @Param("name")String name);
}
