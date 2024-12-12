package org.chdzq.gateway.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.oauth2.sdk.token.BearerTokenError;
import lombok.extern.slf4j.Slf4j;
import org.chdzq.common.core.entity.Result;
import org.chdzq.common.core.result.ResultError;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * WebFlux 响应工具类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/29 21:05
 */
@Slf4j
public class WebFluxUtil {
    private WebFluxUtil() {}

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static Mono<Void> writeErrorResponse(ServerHttpResponse response, BearerTokenError tokenError) {
        HttpStatus status = determineHttpStatus(tokenError);
        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.getHeaders().setAccessControlAllowOrigin("*");
        response.getHeaders().setCacheControl("no-cache");
        String responseBody;
        try {
            responseBody = objectMapper.writeValueAsString(Result.fail(ResultError.ACCESS_TOKEN_ERROR));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            responseBody = "";
        }
        DataBuffer buffer = response.bufferFactory().wrap(responseBody.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer))
                .doOnError(error -> {
                    DataBufferUtils.release(buffer);
                    log.error("Error writing response: {}", error.getMessage());
                });
    }

    private static HttpStatus determineHttpStatus(BearerTokenError error) {
        if (Objects.isNull(error)) {
            return HttpStatus.BAD_REQUEST;
        }
        int status = error.getHTTPStatusCode();
        HttpStatus httpStatus;
        try {
            httpStatus = HttpStatus.valueOf(status);
        } catch (IllegalArgumentException exception) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return httpStatus;
    }
}
