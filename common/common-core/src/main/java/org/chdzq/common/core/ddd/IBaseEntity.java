package org.chdzq.common.core.ddd;

import java.io.Serializable;

/**
 * 基础实体
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 02:02
 */
public interface IBaseEntity<ID extends Serializable> extends IAggregateRoot<ID> {

}
