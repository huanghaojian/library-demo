package xyz.hhjian.lib.entity.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.hhjian.lib.entity.enums.BookStatusEnum;

/**
 * <p>图书类</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
@Data
@NoArgsConstructor
@TableName("book")
public class Book {
    @TableId("book_id")
    private Long bookId;
    private String isbn;
    private String title;
    private String publisher;
    private String pubdate;
    private String author;
    private String coverUrl;
    private BookStatusEnum status;
    private String bookrackId;
    private String classify;
    private String category;
}
