package xyz.hhjian.lib.entity.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * <p>登录表单</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.21
 */
@Data
@NoArgsConstructor
public class LoginBO {
    @Size(min = 3, max = 20, message = "{user.username.size}")
    private String username;
    @Size(min = 3, max = 20, message = "{user.password.size}")
    private String password;
}
