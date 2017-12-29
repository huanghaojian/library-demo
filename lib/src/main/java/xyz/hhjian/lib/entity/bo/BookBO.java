package xyz.hhjian.lib.entity.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>添加书籍</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.11.05
 */
@Data
@NoArgsConstructor
public class BookBO {
    private String isbn;
    private String bookrackId;
    private String classify;
}
