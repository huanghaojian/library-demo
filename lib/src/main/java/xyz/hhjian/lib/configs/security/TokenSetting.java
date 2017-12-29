package xyz.hhjian.lib.configs.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Token相关属性设置</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.31
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "security.jwt")
public class TokenSetting {
    private String tokenHeaderName;
    private String tokenHead;
}
