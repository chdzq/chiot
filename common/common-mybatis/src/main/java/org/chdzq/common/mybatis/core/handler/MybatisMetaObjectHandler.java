package org.chdzq.common.mybatis.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.chdzq.common.mybatis.domain.BaseDO;
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
        if (metaObject.getOriginalObject() instanceof BaseDO obj) {
            obj.setCreatedTime(LocalDateTime.now());
            obj.setUpdatedTime(LocalDateTime.now());
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "deleteStatus", Integer.class, 0);
//        if (TokenUtils.tokenIsExist()) {
//            this.strictInsertFill(metaObject, "createUserId", String.class, TokenUtils.getUserIdByToken());
//            this.strictInsertFill(metaObject, "updateUserId", String.class, TokenUtils.getUserIdByToken());
//        }
    }
}
