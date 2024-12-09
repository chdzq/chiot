package org.chdzq.common.mybatis.core.service;

import com.baomidou.mybatisplus.annotation.DbType;
import org.chdzq.common.core.utils.SpringUtil;

/**
 * SQL相关常量类
 *
 * @author chdzq
 * @create 2022/11/11
 */
public class DbTypeContext {

    /**
     * 数据库的类型
     */
    public static DbTypeFinalizer DB_TYPE_FINALIZER;

    public static DbType getDbType() {
        if (DB_TYPE_FINALIZER == null) {
            DB_TYPE_FINALIZER = SpringUtil.getBean(DbTypeFinalizer.class);
        }
        return DB_TYPE_FINALIZER.getDbType();
    }

}
