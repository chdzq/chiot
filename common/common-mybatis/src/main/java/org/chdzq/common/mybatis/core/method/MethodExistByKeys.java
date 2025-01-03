package org.chdzq.common.mybatis.core.method;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据主键列表查询
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/3 09:42
 */
public class MethodExistByKeys extends AbstractMethod {

    private static final String METHOD_NAME = "isExistedByKeys";
    private static final String SQL = "<script>%s SELECT %s FROM %s %s %s %s\n</script>";
    public MethodExistByKeys() {
        this(METHOD_NAME);
    }

    /**
     * @param name 方法名
     */
    public MethodExistByKeys(String name) {
        super(name);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = String.format(
                SQL,
                sqlFirst(),
                tableInfo.getKeyColumn(),
                tableInfo.getTableName(),
                sqlWhereEntityWrapper(true, tableInfo),
                sqlOrderBy(tableInfo),
                sqlComment()
        );
        SqlSource sqlSource = super.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatementForOther(mapperClass, methodName, sqlSource, tableInfo.getKeyType());
    }
}
