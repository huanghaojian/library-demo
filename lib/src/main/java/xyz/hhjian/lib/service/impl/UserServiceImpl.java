package xyz.hhjian.lib.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.hhjian.lib.entity.domain.User;
import xyz.hhjian.lib.mapper.UserMapper;
import xyz.hhjian.lib.service.UserService;

/**
 * <p>用户业务</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
