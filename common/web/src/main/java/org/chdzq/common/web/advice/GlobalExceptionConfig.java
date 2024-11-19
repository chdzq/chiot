package org.chdzq.common.web.advice;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.chdzq.common.core.entity.R;
import org.chdzq.common.core.exceptions.BusinessException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

/**
 * @Description 异常处理
 * @Author chdzq
 * @Date 2024/11/14
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionConfig {


    /**
     * 处理 SpringMVC 请求参数缺失
     *
     * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public R<?> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        log.warn("MissingServletRequestParameterException Handler: {}", ex.getMessage());
        return R.fail(String.format("请求参数缺失:%s", ex.getParameterName()));
    }

    /**
     * 处理 SpringMVC 请求参数类型错误
     *
     * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R<?> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        log.warn("MethodArgumentTypeMismatchException Handler: {}", ex.getMessage());
        return R.fail(String.format("请求参数类型错误:%s", ex.getMessage()));
    }

    /**
     * 处理 SpringMVC 参数校验不正确
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<?> methodArgumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException ex) {
        log.warn("MethodArgumentNotValidException Handler: {}", ex.getMessage());
        FieldError fieldError = ex.getBindingResult().getFieldError();
        if (Objects.nonNull(fieldError)) {
            return R.fail(String.format("请求参数[%s]不正确:%s", ex.getParameter().getParameterName(), fieldError.getDefaultMessage()));
        }
        return R.fail(String.format("请求参数[%s]不正确", ex.getParameter().getParameterName()));
    }

    /**
     * 跟单一参数校验不一样的是JavaBean的校验方式需要将@Validated写在方法参数，而不是类上。如果出现了参数校验不通过，同样的也会抛出一个异常，BindException。
     */
    @ExceptionHandler(BindException.class)
    public R<?> bindExceptionHandler(BindException ex) {
        log.warn("BindException Handler: {}", ex.getMessage());
        FieldError fieldError = ex.getFieldError();
        if (Objects.nonNull(fieldError)) {
            return R.fail(String.format("请求参数[%s]不正确:%s", fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return R.fail(String.format("请求参数[%s]不正确", fieldError.getField()));
    }

    /**
     * 处理 本地参数校验时，抛出的 ValidationException 异常
     */
    @ExceptionHandler(value = ValidationException.class)
    public R<?> validationException(ValidationException ex) {
        log.warn("ValidationException Handler:{}", ex.getMessage());
        return R.fail("系统异常");
    }


    /**
     * 处理 SpringMVC 请求方法不正确
     *
     * 例如说，A 接口的方法为 GET 方式，结果请求方法为 POST 方式，导致不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<?> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.warn("HttpRequestMethodNotSupportedException Handler: {}", ex.getMessage());
        return R.fail(String.format("请求方法不正确:%s", ex.getMessage()));
    }

    /**
     * Global Exception
     *
     * @param exception Exception
     * @return R
     */
    @ExceptionHandler(BusinessException.class)
    public R<String> businessException(BusinessException exception) {
        log.error("Business Exception Handler: {}", exception.getMessage());
        return R.fail(exception.getCode(), exception.getMessage());
    }


    /**
     * Global Exception
     *
     * @param exception Exception
     * @return R
     */
    @ExceptionHandler(Exception.class)
    public R<String> globalException(Exception exception) {
        log.error("Global Exception Handler: {}", exception.getMessage());
        return R.fail(exception.getMessage());
    }


}
