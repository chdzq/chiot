package org.chdzq.common.mybatis.core.service;

import com.baomidou.mybatisplus.annotation.DbType;

/**
 * @author chdzq
 * @version 1.0
 * @description: DbType 默认实现类
 * @date 2024/11/20 22:18
 */
public class DbTypeDefaultFinalizer implements DbTypeFinalizer{
    /**
     * 数据库的类型
     */
    public static DbType DB_TYPE;

    public static void init(DbType dbType) {
        DB_TYPE = dbType;
    }

    @Override
    public DbType getDbType() {
        return DB_TYPE;
    }
}
