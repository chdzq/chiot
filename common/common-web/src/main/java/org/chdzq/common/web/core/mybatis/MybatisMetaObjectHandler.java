package org.chdzq.common.web.core.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.chdzq.common.core.constants.Constant;
import org.chdzq.common.mybatis.domain.BaseDO;
import org.chdzq.common.web.UserContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @description: 默认填充值
 * @author chdzq
 * @version 1.0
 * @date 2024/11/21 23:09
 */
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = UserContext.getUserId();
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "createdBy", Long.class, userId);
        this.strictInsertFill(metaObject, "updatedTime", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "updatedBy", Long.class, userId);
        this.strictInsertFill(metaObject, "deleted", Integer.class, Constant.UNDELETED);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        Long userId = UserContext.getUserId();
        this.strictInsertFill(metaObject, "updatedTime", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "updatedBy", Long.class, userId);

    }
}
