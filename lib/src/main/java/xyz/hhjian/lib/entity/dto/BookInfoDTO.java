package xyz.hhjian.lib.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>图书信息</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.20
 */
@Data
@NoArgsConstructor
public class BookInfoDTO {
    private String isbn;
    private String title;
    private String publisher;
    private String pubdate;
    private String author;
    private String coverUrl;
    private String bookrackId;
    private String classify;
    private String category;
    private Integer totalNum;
    private Integer currentNum;
}
