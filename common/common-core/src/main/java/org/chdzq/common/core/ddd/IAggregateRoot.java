package org.chdzq.common.core.ddd;

import java.io.Serializable;

/**
 * 聚合根
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/11 22:34
 */
public interface IAggregateRoot<ID extends Serializable> {

    /**
     * 标识
     * @return
     */
    ID getIdentifier();
}
