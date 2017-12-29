package xyz.hhjian.lib;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.ibatis.session.RowBounds;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.hhjian.lib.entity.domain.Order;
import xyz.hhjian.lib.entity.domain.User;
import xyz.hhjian.lib.entity.enums.BookStatusEnum;
import xyz.hhjian.lib.entity.enums.OrderStatusEnum;
import xyz.hhjian.lib.entity.enums.RoleEnum;
import xyz.hhjian.lib.mapper.BookMapper;
import xyz.hhjian.lib.mapper.OrderMapper;
import xyz.hhjian.lib.mapper.UserMapper;

/**
 * <p>Mapper 测试</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder()
public class MapperTest {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetStatusById() {
        BookStatusEnum bookStatusEnum = bookMapper.getStatusByOrderId(1000000L);
        Assert.assertNotNull(bookStatusEnum);
    }

    @Test
    public void testGetStatusByOrderId() {
        OrderStatusEnum orderStatusEnum = orderMapper.getStatusById(1000000L);
        Assert.assertNotNull(orderStatusEnum);
    }

    @Test
    @Ignore("test pass")
    public void testUpdateStatusById() {
        Long orderId = 1000000L;
        Integer result = orderMapper.updateStatusById(orderId, OrderStatusEnum.RETURN);
        System.out.println("result:" + result);
        String status = orderMapper.getStatusById(orderId).getDesc();
        Assert.assertEquals(status, OrderStatusEnum.RETURN.getDesc());
    }

    @Test
    public void testListOrder() {
        EntityWrapper<User> userWrapper = new EntityWrapper<>();
        User user = new User();
        user.setUserId(48641148789L);
        userWrapper.setEntity(user);
        RowBounds rowBounds = new RowBounds(1, 10);
        userMapper.selectPage(rowBounds, userWrapper);
    }
}
