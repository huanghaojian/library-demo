package xyz.hhjian.lib.controller.back;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import xyz.hhjian.lib.entity.bo.BookBO;
import xyz.hhjian.lib.entity.domain.Book;
import xyz.hhjian.lib.entity.domain.User;
import xyz.hhjian.lib.entity.dto.OrderInfoDTO;
import xyz.hhjian.lib.entity.dto.Result;
import xyz.hhjian.lib.entity.dto.TokenInfoDTO;
import xyz.hhjian.lib.entity.dto.UserInfoDTO;
import xyz.hhjian.lib.entity.enums.OrderStatusEnum;
import xyz.hhjian.lib.entity.enums.RoleEnum;
import xyz.hhjian.lib.exception.BookException;
import xyz.hhjian.lib.service.impl.BookServiceImpl;
import xyz.hhjian.lib.service.impl.OrderServiceImpl;
import xyz.hhjian.lib.service.impl.UserServiceImpl;
import xyz.hhjian.lib.utils.ResultUtil;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>管理管理员控制器</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
@Api(tags = "管理admin接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/admins")
public class AdminController {

    private final UserServiceImpl userService;
    private final BookServiceImpl bookService;
    private final OrderServiceImpl orderService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserServiceImpl userService, BookServiceImpl bookService, OrderServiceImpl orderService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.bookService = bookService;
        this.orderService = orderService;
        this.passwordEncoder = passwordEncoder;
    }

    @ApiOperation(value = "添加管理员", notes = "需要ROOT权限")
    @PostMapping("/")
    @PreAuthorize("hasRole('ROOT')")
    public Result saveAdmin(@RequestBody User admin) {
        admin.setRole(RoleEnum.ADMIN);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        userService.insert(admin);
        TokenInfoDTO tokenInfoDTO = new TokenInfoDTO();
        tokenInfoDTO.setUserId(admin.getUserId());
        return ResultUtil.success(tokenInfoDTO);
    }

    @ApiOperation(value = "删除管理员", notes = "需要ROOT权限")
    @DeleteMapping("/{adminId}")
    @PreAuthorize("hasRole('ROOT')")
    public Result removeAdmin(@PathVariable("adminId") Long adminId) {
        userService.deleteById(adminId);
        return ResultUtil.success();
    }

    @ApiOperation(value = "修改管理员信息", notes = "需要ROOT或ADMIN权限")
    @PutMapping("/{adminId}")
    @PreAuthorize("hasAnyRole('ROOT','ADMIN')")
    public Result updateAdmin(@PathVariable("adminId") Long adminId, @RequestBody UserInfoDTO updateAdmin) {
        User admin = new User();
        BeanUtils.copyProperties(updateAdmin, admin);
        admin.setUserId(adminId);
        userService.updateById(admin);
        return ResultUtil.success();
    }

    @ApiOperation(value = "获取管理员信息", notes = "ROOT或ADMIN权限,ADMIN只能获取自己的")
    @GetMapping("/{adminId}")
    @PreAuthorize("hasAnyRole('ROOT','ADMIN')")
    public Result getAdmin(@PathVariable("adminId") Long adminId) {
        User user = userService.selectById(adminId);
        UserInfoDTO userInfoDTO = null;
        if (user != null) {
            userInfoDTO = new UserInfoDTO();
            BeanUtils.copyProperties(user, userInfoDTO);
        }
        return ResultUtil.success(userInfoDTO);
    }

    @ApiOperation(value = "获取所有用户信息列表", notes = "需要ROOT权限")
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROOT')")
    public Result listAdmin(@RequestParam(value = "page", defaultValue = "1") Integer page,
                            @ApiParam(allowableValues = "USER,ADMIN,ROOT") @RequestParam(value = "role", required = false) RoleEnum role) {
        Page<User> userPage = new Page<>(page, 10);
        EntityWrapper<User> userWrapper = new EntityWrapper<>();
        User userEntity = new User();
        userEntity.setRole(role);
        userWrapper.setEntity(userEntity);
        List<User> userList = userService.selectPage(userPage, userWrapper).getRecords();
        List<UserInfoDTO> userInfoDTOList = null;
        if (!CollectionUtils.isEmpty(userList)) {
            userInfoDTOList = userList.stream().map(user -> {
                UserInfoDTO userInfoDTO = new UserInfoDTO();
                BeanUtils.copyProperties(user, userInfoDTO);
                return userInfoDTO;
            }).collect(Collectors.toList());
        }
        return ResultUtil.success(userInfoDTOList);
    }

    @ApiOperation(value = "添加图书", notes = "ROOT或ADMIN权限")
    @PostMapping("/books")
    @PreAuthorize("hasAnyRole('ROOT','ADMIN')")
    public Result saveBook(@RequestBody BookBO bookBO) throws BookException {
        Long bookId = bookService.saveBook(bookBO);
        HashMap<String, String> map = new HashMap<>(1);
        map.put("bookId", bookId.toString());
        return ResultUtil.success(map);
    }

//    @ApiOperation(value = "删除图书", notes = "ROOT或ADMIN权限")
//    @DeleteMapping("/books/{bookId}")
//    @PreAuthorize("hasAnyRole('ROOT','ADMIN')")
//    public Result removeBook(@PathVariable("bookId") Long bookId) {
//        bookService.removeBookByBookId(bookId);
//        return ResultUtil.success();
//    }

    @ApiOperation(value = "修改图书", notes = "ROOT或ADMIN权限")
    @PutMapping("/books/{bookId}")
    @PreAuthorize("hasAnyRole('ROOT','ADMIN')")
    public Result updateBook(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        bookService.updateBookByBookId(bookId, book);
        return ResultUtil.success();
    }

    @ApiOperation(value = "查看所有订单", notes = "ROOT或ADMIN权限")
    @PutMapping("/orders/list")
    @PreAuthorize("hasAnyRole('ROOT','ADMIN')")
    public Result listOrder(@RequestParam(value = "status", required = false) OrderStatusEnum orderStatusEnum,
                            @RequestParam(value = "orderId", required = false) Long orderId,
                            @RequestParam(value = "page", defaultValue = "1") Integer page) {
        List<OrderInfoDTO> orderList = orderService.listOrderByPage(null, orderStatusEnum, orderId, page);
        return ResultUtil.success(orderList);
    }

    @ApiOperation(value = "确认图书已借出", notes = "ROOT或ADMIN权限")
    @PutMapping("/{adminId}/orders/{orderId}/borrow")
    @PreAuthorize("hasAnyRole('ROOT','ADMIN')")
    public Result confirmBorrow(@PathVariable("adminId") Long adminId, @PathVariable("orderId") Long orderId) {
        orderService.confirmBorrow(adminId, orderId);
        return ResultUtil.success();
    }

    @ApiOperation(value = "确认图书已归还", notes = "ROOT或ADMIN权限")
    @PutMapping("/{adminId}/orders/{orderId}/return")
    @PreAuthorize("hasAnyRole('ROOT','ADMIN')")
    public Result confirmReturn(@PathVariable("adminId") Long adminId, @PathVariable("orderId") Long orderId) {
        orderService.confirmReturn(adminId, orderId);
        return ResultUtil.success();
    }
}
