package org.chdzq.common.mybatis.core.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.session.Configuration;

import java.util.List;

/**
 * 用来快速判断是否存在
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/3 10:00
 */
public class LogicSqlInjector extends DefaultSqlInjector {
    public LogicSqlInjector() {}

    public List<AbstractMethod> getMethodList(Configuration configuration, Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(configuration, mapperClass, tableInfo);
        methodList.add(new MethodExistByKey());
        methodList.add(new MethodExistByKeys());
        return methodList;
    }
}
