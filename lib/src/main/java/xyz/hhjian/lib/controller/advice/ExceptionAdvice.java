package xyz.hhjian.lib.controller.advice;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.UndeclaredThrowableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import xyz.hhjian.lib.entity.dto.Result;
import xyz.hhjian.lib.exception.BookException;
import xyz.hhjian.lib.exception.ValidateException;
import xyz.hhjian.lib.utils.ResultUtil;

import java.util.Arrays;

/**
 * <p>程序异常处理</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.21
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 0 - this book not exist
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BookException.class)
    public Result handleBookNotExistException(BookException e) {
        return ResultUtil.failure(e.getErrcode(), e.getMessage());
    }

    /**
     * 400 - bad request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            ValidateException.class
    })
    public Result handleValidateException(HttpMessageNotReadableException e) {
        getErrLog(e);
        return ResultUtil.failure(400, e.getMessage());
    }

    /**
     * 401 - unauthorized
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            UndeclaredThrowableException.class,
            AuthenticationException.class,
            AccessDeniedException.class
    })
    public Result handleAuthenticationException(Exception e) {
        getErrLog(e);
        return ResultUtil.failure(401, "unauthorized");
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResultUtil.failure(405, "Method Not Allowed");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler({
            HttpMediaTypeNotSupportedException.class,
            MethodArgumentTypeMismatchException.class
    })
    public Result handleHttpMediaTypeNotSupportedException(Exception e) {
        getErrLog(e);
        return ResultUtil.failure(415, "Unsupported Media Type");
    }

    /**
     * 404 - Douban Book Not Found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FeignException.class)
    public Result handleFeignException(FeignException e) {
        getErrLog(e);
        return ResultUtil.failure(e.status(), e.getMessage());
    }

    /**
     * -1 - Unknown error
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        getErrLog(e);
        return ResultUtil.failure(-1, e.getLocalizedMessage());
    }


    public void getErrLog(Throwable e) {
        LOGGER.error("------------------------------------------------------------");
        LOGGER.error("Exception:" + e.getClass().getSimpleName());
        LOGGER.error("Message:" + e.getLocalizedMessage());
        LOGGER.error("Cause:" + e.getCause());
        Arrays.asList(e.getStackTrace())
                .parallelStream()
//                .filter(err -> err.toString().contains("xyz.hhjian"))
                .forEach(err -> LOGGER.error(err.toString()));
        LOGGER.error("------------------------------------------------------------");
    }
}
