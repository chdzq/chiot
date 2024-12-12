package org.chdzq.authentication.repository.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.chdzq.common.core.vo.Password;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 密码转换
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 21:52
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Password.class)
public class PasswordTypeHandler extends BaseTypeHandler<Password> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Password parameter, JdbcType jdbcType) throws SQLException {
            ps.setString(i, parameter.getPassword());
    }

    @Override
    public Password getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (!StringUtils.hasText(rs.getString(columnName))) {
            return null;
        }
        return new Password(rs.getString(columnName), false);
    }

    @Override
    public Password getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (!StringUtils.hasText(rs.getString(columnIndex))) {
            return null;
        }
        return new Password(rs.getString(columnIndex), false);
    }

    @Override
    public Password getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (!StringUtils.hasText(cs.getString(columnIndex))) {
            return null;
        }
        return new Password(cs.getString(columnIndex), false);
    }
}
