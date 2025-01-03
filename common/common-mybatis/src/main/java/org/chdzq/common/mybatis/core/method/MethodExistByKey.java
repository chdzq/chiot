package org.chdzq.common.mybatis.core.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据主键查询
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/3 09:42
 */
public class MethodExistByKey extends AbstractMethod {

    private static final String METHOD_NAME = "isExistedByKey";
    private static final String SQL = "<script>%s SELECT %s FROM %s %s %s\n</script>";

    public MethodExistByKey() {
        this(METHOD_NAME);
    }

    public MethodExistByKey(String methodName) {
        super(methodName);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = String.format(
                SQL,
                sqlFirst(),
                tableInfo.getKeyColumn(),
                tableInfo.getTableName(),
                sqlWhereEntityWrapper(true, tableInfo),
                sqlComment()
        );
        SqlSource sqlSource = super.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatementForOther(mapperClass, METHOD_NAME, sqlSource, tableInfo.getKeyType());
    }

}
