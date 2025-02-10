package org.chdzq.common.core.ddd;

import lombok.Data;

/**
 * 实体基类
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 17:23
 */
@Data
public class BaseEntity {

    /**
     *  是否是修改的数据
     */
    private boolean dirty = false;

}
