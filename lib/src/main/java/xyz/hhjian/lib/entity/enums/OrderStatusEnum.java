package xyz.hhjian.lib.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * <p>订单状态枚举类</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
public enum OrderStatusEnum implements IEnum {
    /**
     * 订单状态: 用户取消订单
     */
    CANCEL(-1, "已取消"),
    /**
     * 订单状态: 用户提交申请
     */
    APPLY(0, "申请中"),
    /**
     * 订单状态: 图书已借出
     */
    BORROW(1, "已借出"),
    /**
     * 订单状态: 用户已归还图书
     */
    RETURN(2, "已归还");

    private int status;
    private String desc;

    OrderStatusEnum(final int status, final String desc) {
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
