package xyz.hhjian.lib.exception;

/**
 * <p>字段校验</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.21
 */
public class ValidateException extends Exception {
    private Integer errcode;

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(Integer errcode, String message) {
        super(message);
        this.errcode = errcode;
    }

    public Integer getErrcode() {
        return errcode;
    }
}
