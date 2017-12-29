package xyz.hhjian.lib.controller.front;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import xyz.hhjian.lib.entity.dto.BookInfoDTO;
import xyz.hhjian.lib.entity.dto.Result;
import xyz.hhjian.lib.service.impl.BookServiceImpl;
import xyz.hhjian.lib.utils.ResultUtil;

/**
 * <p>图书信息控制器</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
@Api(tags = "图书信息接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookServiceImpl bookService;

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @ApiOperation("根据isbn获取图书详细信息")
    @ApiImplicitParam(name = "Authorization", value = "不用填", required = false, paramType = "header")
    @GetMapping("/isbn/{isbn}")
    public Result getBookByIsbn(@PathVariable("isbn") String isbn) {
        BookInfoDTO book = bookService.getBookByIsbn(isbn);
        return ResultUtil.success(book);
    }

    @ApiOperation("获取图书信息列表,查书")
    @ApiImplicitParam(name = "Authorization", value = "不用填", required = false, paramType = "header")
    @GetMapping("/list")
    public Result listBook(@RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "isbn", required = false) String isbn,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "author", required = false) String author,
                           @RequestParam(value = "publisher", required = false) String publisher) {
        return ResultUtil.success(bookService.listBookByPage(page, isbn, title, author, publisher));
    }
}
