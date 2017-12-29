package xyz.hhjian.lib.configs.cros;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>CORS跨域配置</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.31
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "cors.configuration")
public class CorsConfig extends WebMvcConfigurerAdapter {

    /**
     * Access-Control-Allow-Origin
     * 允许访问的客户端的域名
     */
    private String[] allowedOrigins;
    /**
     * Access-Control-Allow-Credentials
     * 是否允许请求带有验证信息，若要获取客户端域下的cookie时，需要将其设置为true。
     */
    private Boolean allowCredentials;
    /**
     * Access-Control-Allow-Headers
     * 允许服务端访问的客户端请求头
     */
    private String[] allowedHeaders;
    /**
     * Access-Control-Allow-Methods
     * 允许访问的HTTP请求方法
     */
    private String[] allowedMethods;
    private Integer maxAge;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowCredentials(allowCredentials)
                .allowedHeaders(allowedHeaders)
                .allowedMethods(allowedMethods)
                .maxAge(maxAge);
    }
}
