package xyz.hhjian.lib.configs.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.Properties;

/**
 * <p>Druid数据源配置</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
@Configuration
public class DruidConfig {

    @Autowired
    private DruidSettings druidSettings;

    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(druidSettings.getUrl());
        dataSource.setUsername(druidSettings.getUsername());
        dataSource.setPassword(druidSettings.getPassword());
        dataSource.setDriverClassName(druidSettings.getDriverClassName());

        dataSource.setInitialSize(druidSettings.getInitialSize());
        dataSource.setMinIdle(druidSettings.getMinIdle());
        dataSource.setMaxActive(druidSettings.getMaxActive());
        dataSource.setMaxWait(druidSettings.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(druidSettings.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druidSettings.getMinEvictableIdleTimeMillis());
        dataSource.setValidationQuery(druidSettings.getValidationQuery());
        dataSource.setTestWhileIdle(druidSettings.isTestWhileIdle());
        dataSource.setTestOnBorrow(druidSettings.isTestOnBorrow());
        dataSource.setTestOnReturn(druidSettings.isTestOnReturn());
        dataSource.setPoolPreparedStatements(druidSettings.isPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidSettings.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            dataSource.setFilters(druidSettings.getFilters());
        } catch (SQLException e) {
            System.err.println("druid configuration initialization filter: " + e);
        }
        String connectPropertiesStr = druidSettings.getConnectionProperties();
        String[] connectPropertiesArray = connectPropertiesStr.split(";");
        Properties connectProperties = new Properties();
        for (String connectPropertyStr : connectPropertiesArray) {
            String[] obj = connectPropertyStr.split("=");
            connectProperties.put(obj[0], obj[1]);
        }
        dataSource.setConnectProperties(connectProperties);
        dataSource.setUseGlobalDataSourceStat(druidSettings.isUseGlobalDataSourceStat());
        return dataSource;
    }
}
