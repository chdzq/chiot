package org.chdzq.system.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.chdzq.common.mybatis.domain.BaseDO;

/**
 * 字典表
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 09:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dictionary")
public class SystemDictionaryDO extends BaseDO {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 字典类型编码
     */
    private String code;

    /**
     * 字典类型编码
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态(1:正常;0:禁用)
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}
