package xyz.hhjian.lib.entity.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import xyz.hhjian.lib.entity.enums.RoleEnum;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * <p>用户类</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
@Data
@NoArgsConstructor
@TableName("user")
public class User {
    @TableId("user_id")
    private Long userId;
    @Size(min = 3, max = 20, message = "{user.username.size}")
    @NotBlank(message = "{user.username.notBlank}")
    private String username;
    @Size(min = 3, max = 20, message = "{user.nickname.size}")
    @NotBlank(message = "{user.nickname.notBlank}")
    private String nickname;
    @NotBlank(message = "{user.password.notBlank}")
    @Size(min = 6, max = 16, message = "{user.password.size}")
    private String password;
    @JsonIgnore
    private RoleEnum role;
    @Pattern(regexp = "[1][3,4,5,6,7,8]\\d{9}", message = "{user.phone.regexp}")
    private String tel;
}
