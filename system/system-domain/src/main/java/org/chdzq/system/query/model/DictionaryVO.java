package org.chdzq.system.query.model;

import lombok.Data;
import lombok.Value;
import org.chdzq.common.core.enums.StatusEnum;

/**
 * 字典类型展示类
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 23:35
 */
@Data
public class DictionaryVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 字典类型编码
     */
    private String code;

    /**
     * 字典项名称
     */
    private String name;

    /**
     * 状态(1:正常;0:禁用)
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

}
