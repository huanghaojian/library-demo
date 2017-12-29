package xyz.hhjian.lib.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.hhjian.lib.entity.enums.RoleEnum;

/**
 * <p>用户信息</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
@Data
@NoArgsConstructor
public class UserInfoDTO {
    private Long userId;
    private String username;
    private String nickname;
    private String tel;
    private RoleEnum role;
}
