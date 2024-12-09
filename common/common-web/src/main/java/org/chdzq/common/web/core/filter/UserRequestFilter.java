package org.chdzq.common.web.core.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chdzq.common.core.constants.RequestHeaderConstant;
import org.chdzq.common.web.UserContext;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author chdzq
 * @version 1.0
 * @description:
 * @date 2024/11/21 23:26
 */
public class UserRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String userId = request.getHeader(RequestHeaderConstant.X_USER_ID);
        if (StringUtils.hasLength(userId)) {
            UserContext.setUserId(userId);
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            UserContext.clearUserId();
        }
    }
}
