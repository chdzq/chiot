package org.chdzq.common.core.ddd;

import org.springframework.transaction.annotation.Transactional;

/**
 * 基础能力抽象类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 00:01
 */
public abstract class BaseAbility<T extends ICommand, R> {
    /**
     * 能力点执行
     *
     * @param cmd
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public R execute(T cmd) {
        try {
            //初始化上下文
            AbilityContext.initContext();

            //能力点执行前的校验
            doChecking(cmd);

            //执行能力业务
            return doExecuting(cmd);

        } finally {
            AbilityContext.clearContext();
        }
    }

    /**
     * 能力参数校验
     *
     * @param cmd 命令
     * @return
     */
    public abstract void doChecking(T cmd);

    /**
     * 执行能力业务
     *
     * @param cmd 命令
     * @return 目标返回对象
     */
    public abstract R doExecuting(T cmd);
}
