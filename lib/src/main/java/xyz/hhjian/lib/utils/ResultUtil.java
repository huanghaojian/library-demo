package xyz.hhjian.lib.utils;

import xyz.hhjian.lib.entity.dto.Result;

/**
 * <p>接口返回值工具类</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
public class ResultUtil {
    public static Result success(Object object) {
        Result response = new Result();
        response.setErrcode(0);
        response.setErrmsg("ok");
        response.setData(object);
        return response;
    }

    public static Result success() {
        return success(null);
    }

    public static Result failure() {
        return failure(-1, "发生未知错误");
    }

    public static Result failure(Integer errcode) {
        Result result = new Result();
        result.setErrcode(errcode);
        return result;
    }

    public static Result failure(String errmsg) {
        Result result = new Result();
        result.setErrmsg(errmsg);
        return result;
    }

    public static Result failure(Integer errcode, String errmsg) {
        Result result = new Result();
        result.setErrcode(errcode);
        result.setErrmsg(errmsg);
        return result;
    }
}
