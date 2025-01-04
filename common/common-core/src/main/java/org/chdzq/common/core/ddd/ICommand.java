package org.chdzq.common.core.ddd;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 命令接口
 * 主要用于增删改等领域行为
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/11 22:15
 */
public interface ICommand<ROOT extends IBaseEntity<ID>, ID extends Serializable> {

    /**
     * 转换为跟对象
     * @return 跟对象
     */
    ROOT buildEntity();
}
