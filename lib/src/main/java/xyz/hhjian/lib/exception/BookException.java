package xyz.hhjian.lib.exception;

/**
 * <p>自定义图书异常</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.20
 */
public class BookException extends Exception {
    private Integer errcode;

    public BookException(String message) {
        super(message);
    }

    public BookException(Integer errcode, String message) {
        super(message);
        this.errcode = errcode;
    }

    public Integer getErrcode() {
        return errcode;
    }
}