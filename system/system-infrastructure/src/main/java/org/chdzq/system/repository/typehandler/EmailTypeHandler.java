package org.chdzq.system.repository.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.chdzq.common.core.vo.EmailNumber;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 邮箱转换
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 22:05
 */
@MappedTypes(EmailNumber.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class EmailTypeHandler extends BaseTypeHandler<EmailNumber> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, EmailNumber parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getEmail());
    }

    @Override
    public EmailNumber getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (!StringUtils.hasText(rs.getString(columnName))) {
            return null;
        }
        return new EmailNumber(rs.getString(columnName), false);
    }

    @Override
    public EmailNumber getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (!StringUtils.hasText(rs.getString(columnIndex))) {
            return null;
        }
        return new EmailNumber(rs.getString(columnIndex), false);
    }

    @Override
    public EmailNumber getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (!StringUtils.hasText(cs.getString(columnIndex))) {
            return null;
        }
        return new EmailNumber(cs.getString(columnIndex), false);
    }
}
