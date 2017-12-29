package xyz.hhjian.lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import xyz.hhjian.lib.entity.domain.User;
import xyz.hhjian.lib.entity.enums.RoleEnum;
import xyz.hhjian.lib.mapper.UserMapper;

/**
 * <p>设置默认用户</p>
 * <p>所有Spring Beans都初始化之后启动</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.21
 */
@Order(1)
@Component
public class StartupAfterLibApplication implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public StartupAfterLibApplication(PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        addDefaultUser();
    }

    private void addDefaultUser() {
        User hhjian = userMapper.selectByUsername("hhjian");
        if (hhjian == null) {
            // 设置ROOT用户
            User root = new User();
            root.setUsername("hhjian");
            root.setPassword(passwordEncoder.encode("123456"));
            root.setTel("13826828355");
            root.setNickname("黃豪健");
            root.setRole(RoleEnum.ROOT);
            userMapper.insert(root);
            // 设置ADMIN用户
            User admin = new User();
            admin.setUsername("201511671107");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setTel("13458419548");
            admin.setNickname("管理");
            admin.setRole(RoleEnum.ADMIN);
            userMapper.insert(admin);
            // 设置USER用户
            User user = new User();
            user.setUsername("hao");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setTel("13420118481");
            user.setNickname("豪");
            user.setRole(RoleEnum.USER);
            userMapper.insert(user);

            System.out.println("INIT USER TABLE !!!!!");
        }
    }
}
