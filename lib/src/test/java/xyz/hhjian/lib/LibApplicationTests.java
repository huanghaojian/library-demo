package xyz.hhjian.lib;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import feign.FeignException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.hhjian.lib.entity.domain.User;
import xyz.hhjian.lib.entity.enums.RoleEnum;
import xyz.hhjian.lib.service.DoubanService;
import xyz.hhjian.lib.service.impl.UserServiceImpl;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibApplicationTests {

    @Resource
    private UserServiceImpl userService;
    @Autowired
    private DoubanService doubanService;

    @Test
    public void contextLoads() {
        try {
            System.out.println(doubanService.getBookById("25862578"));
            System.out.println(doubanService.getBookByIsbn("97875442708"));
        } catch (FeignException e) {
            System.out.println(e.status());
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Ignore
    public void testSaveUser() {
        User user = new User();
        user.setUsername("黃豪");
        user.setPassword("123");
        user.setRole(RoleEnum.ROOT);
        user.setTel("13826828355");
        Assert.assertTrue(userService.insert(user));
        System.out.println(user.getUserId());
    }

    @Test
    @Ignore
    public void testGetUserByName() {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        User userWrapper = new User();
        userWrapper.setUsername("黃豪健");
        wrapper.setEntity(userWrapper);
        User user = userService.selectOne(wrapper);
        Assert.assertNotNull(user);
    }

}
