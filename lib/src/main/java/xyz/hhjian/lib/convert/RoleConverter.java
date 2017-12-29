package xyz.hhjian.lib.convert;

import org.springframework.core.convert.converter.Converter;
import xyz.hhjian.lib.entity.enums.RoleEnum;

import java.util.Arrays;

/**
 * <p>String to RoleEnum</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.29
 */
public class RoleConverter implements Converter<String, RoleEnum> {
    @Override
    public RoleEnum convert(String source) {
        return Arrays.stream(RoleEnum.values())
                .filter(RoleEnum -> RoleEnum.toString().equalsIgnoreCase(source))
                .findFirst().get();
    }
}
