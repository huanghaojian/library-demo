package xyz.hhjian.lib.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.hhjian.lib.constant.Constants;
import xyz.hhjian.lib.convert.DouBanDTOConverter;
import xyz.hhjian.lib.entity.bo.BookBO;
import xyz.hhjian.lib.entity.domain.Book;
import xyz.hhjian.lib.entity.dto.BookInfoDTO;
import xyz.hhjian.lib.entity.dto.DouBanDTO;
import xyz.hhjian.lib.exception.BookException;
import xyz.hhjian.lib.mapper.BookMapper;
import xyz.hhjian.lib.service.BookSerivce;
import xyz.hhjian.lib.service.DoubanService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>图书业务</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
@Service
public class BookServiceImpl implements BookSerivce {
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Autowired
    private DoubanService doubanService;

    @Override
    public Long saveBook(BookBO bookBO) throws BookException {
        DouBanDTO douBanDTO = doubanService.getBookByIsbn(bookBO.getIsbn());
        Book book = new DouBanDTOConverter().convert(douBanDTO);
        bookMapper.insert(book);
        return book.getBookId();
    }

    @Override
    public void removeBookByBookId(Long bookId) {
        bookMapper.deleteById(bookId);
    }

    @Override
    public void updateBookByBookId(Long bookId, Book book) {
        book.setBookId(bookId);
        bookMapper.updateById(book);
    }

//    @Override
//    public BookInfoDTO getBookByBookId(Long bookId) {
//        Book book = bookMapper.selectById(bookId);
//        BookInfoDTO bookInfoDTO = null;
//        if (book != null) {
//            bookInfoDTO = new BookInfoDTO();
//            BeanUtils.copyProperties(book, bookInfoDTO);
//            // 获取该图书一共有多少本
//            Integer totalNum = bookMapper.countTotalByIsbn(book.getIsbn());
//            Integer currentNum = bookMapper.countCurrentByIsbn(book.getIsbn());
//            bookInfoDTO.setTotalNum(totalNum);
//            bookInfoDTO.setCurrentNum(currentNum);
//        }
//        return bookInfoDTO;
//    }

    @Override
    public BookInfoDTO getBookByIsbn(String isbn) {
        // TODO
        Book book = bookMapper.selectByIsbn(isbn);
        BookInfoDTO bookInfoDTO = null;
        if (book != null) {
            bookInfoDTO = new BookInfoDTO();
            BeanUtils.copyProperties(book, bookInfoDTO);
            // 获取该图书一共有多少本
            Integer totalNum = bookMapper.countTotalByIsbn(book.getIsbn());
            Integer currentNum = bookMapper.countCurrentByIsbn(book.getIsbn());
            bookInfoDTO.setTotalNum(totalNum);
            bookInfoDTO.setCurrentNum(currentNum);
        }
        return bookInfoDTO;
    }

    @Override
    public List<BookInfoDTO> listBookByPage(Integer page, String isbn, String title, String author, String publisher) {
        EntityWrapper<Book> bookWrapper = new EntityWrapper<>();
        if (isbn != null) {
            bookWrapper.like("isbn", isbn);
        }
        if (title != null) {
            bookWrapper.like("title", title);
        }
        if (author != null) {
            bookWrapper.like("author", author);
        }
        if (publisher != null) {
            bookWrapper.like("publisher", publisher);
        }
        RowBounds rowBounds = new RowBounds((page - 1) * Constants.LIMIT, Constants.LIMIT);
        String columns = "isbn, title, publisher, pubdate, author, cover_url, bookrack_id, classify, category";
        bookWrapper.setSqlSelect(columns);
        bookWrapper.groupBy(columns);
        List<Book> books = bookMapper.selectPage(rowBounds, bookWrapper);
        return books.stream()
                .map(book -> {
                    BookInfoDTO bookInfoDTO = new BookInfoDTO();
                    BeanUtils.copyProperties(book, bookInfoDTO);
                    return bookInfoDTO;
                })
                .collect(Collectors.toList());
    }
}
