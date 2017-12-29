package xyz.hhjian.lib.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.hhjian.lib.entity.enums.OrderStatusEnum;

import java.util.Date;

/**
 * <p>借阅订单信息</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.20
 */
@Data
@NoArgsConstructor
public class OrderInfoDTO {
    private Long orderId;
    private Date gmtApply;
    private Date gmtBorrow;
    private Date gmtReturn;
    private OrderStatusEnum status;
    private Long bookId;
    private String title;
    private String isbn;
}
