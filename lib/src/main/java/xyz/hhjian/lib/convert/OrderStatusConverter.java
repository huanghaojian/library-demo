package xyz.hhjian.lib.convert;

import org.springframework.core.convert.converter.Converter;
import xyz.hhjian.lib.entity.enums.OrderStatusEnum;

import java.util.Arrays;

/**
 * <p>String To OrderStatusEnum</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.22
 */
public class OrderStatusConverter implements Converter<String, OrderStatusEnum> {
    @Override
    public OrderStatusEnum convert(String source) {
        return Arrays.stream(OrderStatusEnum.values())
                .filter(orderStatusEnum -> orderStatusEnum.getValue().toString().equals(source))
                .findFirst()
                .get();
    }
}
