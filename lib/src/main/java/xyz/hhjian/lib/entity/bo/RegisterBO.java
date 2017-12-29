package xyz.hhjian.lib.entity.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * <p>注册表单</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.22
 */
@Setter
@Getter
@NoArgsConstructor
public class RegisterBO extends LoginBO {
    @Size(min = 3, max = 20, message = "{user.nickname.size}")
    @NotBlank(message = "{user.nickname.notBlank}")
    private String nickname;
    @Pattern(regexp = "[1][3,4,5,6,7,8]\\d{9}", message = "{user.phone.regexp}")
    private String tel;
}
