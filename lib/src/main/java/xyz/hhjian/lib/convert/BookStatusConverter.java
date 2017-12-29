package xyz.hhjian.lib.convert;

import org.springframework.core.convert.converter.Converter;
import xyz.hhjian.lib.entity.enums.BookStatusEnum;

import java.util.Arrays;

/**
 * <p>String to BookStatusEnum</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.21
 */
public class BookStatusConverter implements Converter<String, BookStatusEnum> {
    @Override
    public BookStatusEnum convert(String source) {
        return Arrays.stream(BookStatusEnum.values())
                .filter(bookStatusEnum -> bookStatusEnum.getValue().toString().equals(source))
                .findFirst().get();
    }
}
