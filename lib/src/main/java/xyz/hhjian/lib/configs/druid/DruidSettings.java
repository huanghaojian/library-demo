package xyz.hhjian.lib.configs.druid;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Druid数据源配置属性类</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
@Data
@Setter
@Getter
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidSettings {
    private String url;

    private String username;

    private String password;

    private String schema;

    private String data;

    private String name;

    private String type;

    private String driverClassName;

    private int initialSize;

    private int minIdle;

    private int maxActive;

    private int maxWait;

    private int timeBetweenEvictionRunsMillis;

    private int minEvictableIdleTimeMillis;

    private String validationQuery;

    private boolean testWhileIdle;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    private boolean poolPreparedStatements;

    private Integer maxPoolPreparedStatementPerConnectionSize;

    private String filters;

    private String connectionProperties;

    private boolean useGlobalDataSourceStat;
}
