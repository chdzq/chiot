package org.chdzq.authentication.query;

import lombok.Data;

/**
 * 查询授权信息
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 16:24
 */
@Data
public class QueryAuthInfo {

    private String username;

    public QueryAuthInfo(String username) {
        this.username = username;
    }
}
