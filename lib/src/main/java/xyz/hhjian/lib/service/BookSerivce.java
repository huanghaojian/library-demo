package xyz.hhjian.lib.service;

import xyz.hhjian.lib.entity.bo.BookBO;
import xyz.hhjian.lib.entity.domain.Book;
import xyz.hhjian.lib.entity.dto.BookInfoDTO;
import xyz.hhjian.lib.exception.BookException;

import java.util.List;

/**
 * <p>图书业务接口</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.20
 */
public interface BookSerivce {
    /**
     * 添加书籍
     *
     * @param bookBO
     * @return
     */
    Long saveBook(BookBO bookBO) throws BookException;

    /**
     * 删除书籍
     *
     * @param bookId
     */
    void removeBookByBookId(Long bookId);

    /**
     * 修改图书信息
     *
     * @param bookId
     * @param book
     */
    void updateBookByBookId(Long bookId, Book book);

    /**
     * 修改图书状态
     *
     * @param bookId
     * @param statusEnum
     */
//    void updateBookStatusByBookId(Long bookId, BookStatusEnum statusEnum);

    /**
     * 根据ID获取图书
     *
     * @param bookId
     * @return
     */
//    Book getBookByBookId(Long bookId);

    /**
     * 根据isbn获取图书
     *
     * @param isbn
     * @return
     */
    BookInfoDTO getBookByIsbn(String isbn);

    /**
     * 分页获取图书列表
     *
     * @param page      页号
     * @param isbn      ISBN
     * @param title     图书标题
     * @param author    作者
     * @param publisher 出版社
     * @return 图书信息集合
     */
    List<BookInfoDTO> listBookByPage(Integer page, String isbn, String title, String author, String publisher);

}
