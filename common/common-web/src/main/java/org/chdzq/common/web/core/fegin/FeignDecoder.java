package org.chdzq.common.web.core.fegin;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.chdzq.common.core.entity.Result;
import org.chdzq.common.core.result.ResultError;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * 解码
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 17:55
 */
public class FeignDecoder implements Decoder {

    private final SpringDecoder decoder;

    public FeignDecoder(SpringDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        Method method = response.request().requestTemplate().methodMetadata().method();
        boolean notTheSame = method.getReturnType() != Result.class;
        if (notTheSame) {
            Type newType = new ParameterizedType() {
                @Override
                public Type[] getActualTypeArguments() {
                    return new Type[]{type};
                }

                @Override
                public Type getRawType() {
                    return Result.class;
                }

                @Override
                public Type getOwnerType() {
                    return null;
                }
            };
            Result<?> result = (Result<?>) this.decoder.decode(response, newType);
            if (Objects.isNull(result)) {
                throw new DecodeException(ResultError.SERVER_ERROR.getCode(), ResultError.SERVER_ERROR.getMessage(), response.request());
            } else if (result.isSuccess()) {
                return result.getData();
            } else {
                throw new DecodeException(result.getCode(), result.getMessage(), response.request());
            }
        }
        return this.decoder.decode(response, type);
    }
}
