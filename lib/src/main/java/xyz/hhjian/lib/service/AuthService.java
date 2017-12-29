package xyz.hhjian.lib.service;

import xyz.hhjian.lib.entity.domain.User;
import xyz.hhjian.lib.entity.dto.TokenInfoDTO;

/**
 * <p>授权业务接口</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.20
 */
public interface AuthService {
    /**
     * 用户注册
     *
     * @param user
     */
    void register(User user);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    TokenInfoDTO login(String username, String password);
}
