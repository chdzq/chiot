package org.chdzq.system.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.chdzq.common.mybatis.domain.BaseDO;

/**
 * 字典分类项
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 15:07
 */
@TableName("sys_dictionary_item")
@Data
public class SystemDictionaryItemDO extends BaseDO {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 字典项所属字典ID
     */
    private Long dictionaryId;

    /**
     * 字典项名称
     */
    @TableField("`key`")
    private String key;

    /**
     * 字典项值
     */
    private String value;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态(1:正常;0:禁用)
     */
    private Integer status;

    /**
     * 是否默认(1:是;0:否)
     */
    private Integer defaulted;
}
