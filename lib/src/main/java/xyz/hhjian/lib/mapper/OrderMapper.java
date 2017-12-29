package xyz.hhjian.lib.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.hhjian.lib.entity.domain.Order;
import xyz.hhjian.lib.entity.enums.OrderStatusEnum;

/**
 * <p>借阅记录仓库</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 根据orderId更新状态
     *
     * @param orderId
     * @param statusEnum
     * @return
     */
    @Update("update borrow_order set status = #{status} where order_id = #{id}")
    Integer updateStatusById(@Param("id") Long orderId, @Param("status") OrderStatusEnum statusEnum);

    /**
     * 获取订单状态码
     *
     * @param id
     * @return
     */
    @Select("select status from borrow_order where order_id = #{id}")
    OrderStatusEnum getStatusById(@Param("id") Long id);
}
