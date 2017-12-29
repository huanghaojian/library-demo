package xyz.hhjian.lib.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * <p>图书状态枚举类</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.20
 */
public enum BookStatusEnum implements IEnum {
    /**
     * 图书已被申请
     */
    FREE(0, "空闲"),
    /**
     * 图书已被借出
     */
    APPLYED(1, "已被申请"),
    /**
     * 图书空闲
     */
    BORROWED(2, "已被借出");
    private int status;
    private String desc;

    BookStatusEnum(final int status, final String desc) {
        this.status = status;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

}
