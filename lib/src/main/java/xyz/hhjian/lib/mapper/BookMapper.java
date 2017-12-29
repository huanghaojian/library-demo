package xyz.hhjian.lib.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.hhjian.lib.entity.domain.Book;
import xyz.hhjian.lib.entity.enums.BookStatusEnum;

import java.util.List;

/**
 * <p>书籍仓库</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
public interface BookMapper extends BaseMapper<Book> {

    /**
     * 获取图书状态码
     *
     * @param orderId
     * @return
     */
    @Select("SELECT status FROM book WHERE book_id = " +
            "(SELECT book_id FROM borrow_order WHERE order_id = #{orderId})")
    BookStatusEnum getStatusByOrderId(@Param("orderId") Long orderId);

    /**
     * 根据orderId修改图书状态码
     *
     * @param orderId
     * @param statusEnum
     * @return
     */
    @Select("UPDATE book SET status = #{status} WHERE book_id = " +
            "(SELECT book_id from borrow_order WHERE order_id = #{orderId})")
    Integer updateStatusByOrderId(@Param("orderId") Long orderId, @Param("status") BookStatusEnum statusEnum);

    /**
     * 根据isbn获取图书总数量
     *
     * @param isbn
     * @return
     */
    @Select("SELECT COUNT(book_id) FROM book WHERE isbn = #{isbn}")
    Integer countTotalByIsbn(@Param("isbn") String isbn);

    /**
     * 根据isbn获取图书当前数量
     *
     * @param isbn
     * @return
     */
    @Select("SELECT COUNT(isbn) FROM book WHERE isbn = #{isbn} and status = 0")
    Integer countCurrentByIsbn(@Param("isbn") String isbn);

    /**
     * 根据isbn获取图书信息
     *
     * @param isbn
     * @return
     */
    @Select("SELECT DISTINCT isbn,title,publisher,pubdate,author,cover_url FROM book WHERE isbn = #{isbn}")
    Book selectByIsbn(@Param("isbn") String isbn);

    /**
     * 根据isbn获取图书Id
     *
     * @param isbn
     * @return
     */
    @Select("SELECT book_id FROM book WHERE isbn = #{isbn} and status = 0")
    List<Long> getBookIdByIsbn(@Param("isbn") String isbn);
}
