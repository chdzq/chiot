package org.chdzq.common.web.core.mybatis.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.chdzq.common.core.vo.PhoneNumber;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 电话号码转换
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 22:12
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({PhoneNumber.class})
public class PhoneTypeHandler extends BaseTypeHandler<PhoneNumber> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, PhoneNumber parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getNumber());
    }

    @Override
    public PhoneNumber getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (!StringUtils.hasText(rs.getString(columnName))) {
            return null;
        }
        return new PhoneNumber(rs.getString(columnName));
    }

    @Override
    public PhoneNumber getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (!StringUtils.hasText(rs.getString(columnIndex))) {
            return null;
        }
        return new PhoneNumber(rs.getString(columnIndex));
    }

    @Override
    public PhoneNumber getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (!StringUtils.hasText(cs.getString(columnIndex))) {
            return null;
        }
        return new PhoneNumber(cs.getString(columnIndex));
    }
}
