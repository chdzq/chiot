package org.chdzq.system.service;

import org.chdzq.common.core.entity.Page;
import org.chdzq.system.query.DictionaryItemPageQuery;
import org.chdzq.system.query.DictionaryPageQuery;
import org.chdzq.system.query.ResourceQuery;
import org.chdzq.system.query.model.DictionaryItemVO;
import org.chdzq.system.query.model.DictionaryVO;
import org.chdzq.system.query.model.ResourceVO;

import java.util.List;

/**
 * 字典查询服务类
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 23:40
 */
public interface DictionaryQueryService {
    /**
     * 根据条件查询字典集列表
     * @param param 查询条件
     * @return 字典集列表
     */
    Page<DictionaryVO> page(DictionaryPageQuery param);

    /**
     * 根据条件查询字典项列表
     * @param param 查询条件
     * @return 字典项列表
     */
    Page<DictionaryItemVO> page(DictionaryItemPageQuery param);
}
