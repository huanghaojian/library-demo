package xyz.hhjian.lib.entity.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.hhjian.lib.entity.enums.OrderStatusEnum;

import java.util.Date;

/**
 * <p>借阅记录类</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
@Data
@NoArgsConstructor
@TableName("borrow_order")
public class Order {
    @TableId("order_id")
    private Long orderId;
    private Date gmtApply;
    private Date gmtBorrow;
    private Date gmtReturn;
    private OrderStatusEnum status;
    private Long bookId;
    private Long userId;
    private Long applyAdminId;
    private Long returnAdminId;
}
