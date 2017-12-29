package xyz.hhjian.lib.service;

import xyz.hhjian.lib.entity.dto.OrderInfoDTO;
import xyz.hhjian.lib.entity.enums.OrderStatusEnum;
import xyz.hhjian.lib.exception.BookException;

import java.util.List;

/**
 * <p>借阅订单业务接口</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.20
 */
public interface OrderService {
    /**
     * 申请借书
     *
     * @param userId 借阅者Id
     * @param isbn   所借书籍isbn
     * @throws BookException
     */
    void applyBorrow(Long userId, String isbn) throws BookException;

    /**
     * 取消申请
     *
     * @param userId
     * @param orderId
     */
    void cancelBorrow(Long userId, Long orderId);

    /**
     * 重新申请借书
     *
     * @param userId  借阅者Id
     * @param orderId 订单号
     * @throws BookException
     */
    void reApplyBorrow(Long userId, Long orderId) throws BookException;

    /**
     * 确认读者已借出图书
     *
     * @param adminId 操作管理员Id
     * @param orderId 订单号
     * @return
     */
    void confirmBorrow(Long adminId, Long orderId);

    /**
     * 确认读者已归还图书
     *
     * @param adminId 操作管理员Id
     * @param orderId 订单号
     * @return
     */
    void confirmReturn(Long adminId, Long orderId);

    /**
     * 分页获取借阅订单,可加条件(订单状态,模糊匹配订单号)
     *
     * @param userId     借阅者Id
     * @param statusEnum 借阅订单状态
     * @param orderId    订单号
     * @param page       页号
     * @return
     */
    List<OrderInfoDTO> listOrderByPage(Long userId, OrderStatusEnum statusEnum, Long orderId, Integer page);
}
