package org.chdzq.common.mybatis.core.service;

import com.baomidou.mybatisplus.annotation.DbType;

/**
 * @Description Db 类型确定
 * @Author chdzq
 * @Date 2024/11/20
 */
@FunctionalInterface
public interface DbTypeFinalizer {

    /**
     * 获取Db 类型
     * @return db类型
     */
    DbType getDbType();
}
