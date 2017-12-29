package xyz.hhjian.lib.convert;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import xyz.hhjian.lib.entity.domain.Book;
import xyz.hhjian.lib.entity.dto.DouBanDTO;

/**
 * <p>DoubanDTO to Book</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.12.07
 */
public class DouBanDTOConverter implements Converter<DouBanDTO, Book> {
    @Override
    public Book convert(DouBanDTO source) {
        Book book = new Book();
        BeanUtils.copyProperties(source, book);
        book.setAuthor(source.getAuthor().get(0));
        book.setCoverUrl(source.getImage());
        if (StringUtils.isEmpty(book.getIsbn()) || StringUtils.isEmpty(book.getTitle())
                || StringUtils.isEmpty(book.getPublisher()) || StringUtils.isEmpty(book.getPubdate())
                || StringUtils.isEmpty(book.getAuthor()) || StringUtils.isEmpty(book.getCoverUrl())) {
            return null;
        } else {
            return book;
        }
    }
}