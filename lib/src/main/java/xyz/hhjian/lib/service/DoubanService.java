package xyz.hhjian.lib.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.hhjian.lib.entity.dto.DouBanDTO;

/**
 * <p>豆瓣查书服务</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.12.07
 */
@FeignClient(name = "douban", url = "https://api.douban.com/v2/book")
public interface DoubanService {
    String fields = "?fields=title,publisher,pubdate,author,image,category,isbn13";

    @GetMapping("/{id}" + fields)
    DouBanDTO getBookById(@PathVariable("id") String id);

    @GetMapping("/isbn/{isbn}" + fields)
    DouBanDTO getBookByIsbn(@PathVariable("isbn") String isbn);
}
