package org.chdzq.common.web.core.fegin;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.Map;

/**
 * 在feign发送请求前取出原来的header并转发
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 17:45
 */
public class FeginRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = attributes.getRequest();
            // 获取请求头
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    // 忽略content-length。因为在复制请求头到新请求时，原始的content-length可能不再准确。
                    if (!"content-length".equalsIgnoreCase(name)) {
                        String values = request.getHeader(name);
                        // 将请求头保存到模板中，除了 Content-Length
                        requestTemplate.header(name, values);
                    }
                }
            }
        }
    }
}
