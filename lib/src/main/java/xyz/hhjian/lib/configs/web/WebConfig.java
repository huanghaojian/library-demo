package xyz.hhjian.lib.configs.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import xyz.hhjian.lib.convert.BookStatusConverter;
import xyz.hhjian.lib.convert.OrderStatusConverter;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>注册自定义类型转换</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.22
 */
@Configuration
public class WebConfig {

    @Bean
    public BookStatusConverter bookStatusConverter() {
        return new BookStatusConverter();
    }

    @Bean
    public OrderStatusConverter orderStatusConverter() {
        return new OrderStatusConverter();
    }

    @Bean("conversionService")
    public ConversionService conversionService() {
        ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(bookStatusConverter());
        converters.add(orderStatusConverter());
        factoryBean.setConverters(converters);
        return factoryBean.getObject();
    }

}
