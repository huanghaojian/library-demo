package xyz.hhjian.lib.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import xyz.hhjian.lib.constant.Constants;
import xyz.hhjian.lib.entity.domain.Book;
import xyz.hhjian.lib.entity.domain.Order;
import xyz.hhjian.lib.entity.dto.OrderInfoDTO;
import xyz.hhjian.lib.entity.enums.BookStatusEnum;
import xyz.hhjian.lib.entity.enums.OrderStatusEnum;
import xyz.hhjian.lib.exception.BookException;
import xyz.hhjian.lib.mapper.BookMapper;
import xyz.hhjian.lib.mapper.OrderMapper;
import xyz.hhjian.lib.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static xyz.hhjian.lib.entity.enums.BookStatusEnum.*;
import static xyz.hhjian.lib.entity.enums.OrderStatusEnum.*;

/**
 * <p>借阅记录业务</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final BookMapper bookMapper;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(BookMapper bookMapper, OrderMapper orderMapper) {
        this.bookMapper = bookMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void applyBorrow(Long userId, String isbn) throws BookException {
        // 是否有书可借
        Integer count = bookMapper.countCurrentByIsbn(isbn);
        List<Long> bookIds = bookMapper.getBookIdByIsbn(isbn);
        if (count > 0 && !CollectionUtils.isEmpty(bookIds)) {
            Order order = new Order();
            order.setUserId(userId);
            order.setBookId(bookIds.get(0));
            order.setStatus(APPLY);
            order.setGmtApply(new Date());
            orderMapper.insert(order);
            // 已申请借书
            bookMapper.updateStatusByOrderId(order.getOrderId(), APPLYED);
        } else {
            throw new BookException("没书可借");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelBorrow(Long userId, Long orderId) {
        // 图书状态改为空闲
        bookMapper.updateStatusByOrderId(orderId, FREE);
        // 订单改为已取消
        orderMapper.updateStatusById(orderId, CANCEL);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reApplyBorrow(Long userId, Long orderId) throws BookException {
        // 图书空闲可外借
        BookStatusEnum bookStatusEnum = bookMapper.getStatusByOrderId(orderId);
        if (FREE.toString().equals(bookStatusEnum.getValue().toString())) {
            // 图书状态改为借出
            bookMapper.updateStatusByOrderId(orderId, BORROWED);
            // 订单改为已借出
            orderMapper.updateStatusById(orderId, BORROW);
        } else {
            throw new BookException(-1, "书籍已被借出或则已被申请!");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmBorrow(Long adminId, Long orderId) {
        // 图书状态改为借出
        bookMapper.updateStatusByOrderId(orderId, BORROWED);
        // 订单改为已借出
        orderMapper.updateStatusById(orderId, BORROW);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmReturn(Long adminId, Long orderId) {
        // 图书状态改为空闲
        bookMapper.updateStatusByOrderId(orderId, FREE);
        // 订单改为已归还
        orderMapper.updateStatusById(orderId, RETURN);
    }

    @Override
    public List<OrderInfoDTO> listOrderByPage(Long userId, OrderStatusEnum orderStatusEnum, Long orderId, Integer page) {
        // 条件设置
        EntityWrapper<Order> orderWrapper = new EntityWrapper<>();
        Order orderEctity = new Order();
        if (userId != null) {
            orderEctity.setUserId(userId);
        }
        if (orderStatusEnum != null) {
            orderEctity.setStatus(orderStatusEnum);
        }
        orderWrapper.setEntity(orderEctity);
        if (orderId != null) {
            orderWrapper.like("order_id", orderId.toString());
        }
        RowBounds rowBounds = new RowBounds((page - 1) * Constants.LIMIT, Constants.LIMIT);
        // 获取订单信息
        List<Order> orderList = orderMapper.selectPage(rowBounds, orderWrapper);
        List<OrderInfoDTO> orderInfoDTOList = null;
        if (!CollectionUtils.isEmpty(orderList)) {
            orderInfoDTOList = orderList.stream()
                    .map(order -> {
                        // Order 转为 OrderInfoDTO
                        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
                        BeanUtils.copyProperties(order, orderInfoDTO);
                        Long bookId = order.getBookId();
                        // OrderInfoDTO添加图书信息
                        Book book = bookMapper.selectById(bookId);
                        orderInfoDTO.setBookId(bookId);
                        orderInfoDTO.setTitle(book.getTitle());
                        orderInfoDTO.setIsbn(book.getIsbn());
                        return orderInfoDTO;
                    })
                    .collect(Collectors.toList());
        }
        return orderInfoDTOList;
    }
}
