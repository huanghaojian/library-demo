package xyz.hhjian.lib.controller.front;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xyz.hhjian.lib.entity.domain.User;
import xyz.hhjian.lib.entity.dto.OrderInfoDTO;
import xyz.hhjian.lib.entity.dto.Result;
import xyz.hhjian.lib.entity.dto.UserInfoDTO;
import xyz.hhjian.lib.entity.enums.OrderStatusEnum;
import xyz.hhjian.lib.exception.BookException;
import xyz.hhjian.lib.service.impl.OrderServiceImpl;
import xyz.hhjian.lib.service.impl.UserServiceImpl;
import xyz.hhjian.lib.utils.ResultUtil;

import java.util.List;

/**
 * <p>用户控制器</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
@Api(tags = "用户接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;
    private final OrderServiceImpl orderService;

    @Autowired
    public UserController(UserServiceImpl userService, OrderServiceImpl orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @ApiOperation(value = "获取用户信息", notes = "需要USER角色")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{userId}")
    public Result getUser(@PathVariable("userId") Long userId) {
        User user = userService.selectById(userId);
        if (user != null) {
            UserInfoDTO userInfoDTO = new UserInfoDTO();
            BeanUtils.copyProperties(user, userInfoDTO);
            return ResultUtil.success(userInfoDTO);
        } else {
            return ResultUtil.failure("user not exist.");
        }
    }

    @ApiOperation(value = "申请借书", notes = "需要USER角色")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{userId}/books/{isbn}")
    public Result applyBorrowBook(@PathVariable("userId") Long userId, @PathVariable("isbn") String isbn) throws BookException {
        orderService.applyBorrow(userId, isbn);
        return ResultUtil.success();
    }

    @ApiOperation(value = "取消订单", notes = "需要USER角色")
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{userId}/orders/{orderId}")
    public Result cancelBorrowBook(@PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId) {
        orderService.cancelBorrow(userId, orderId);
        return ResultUtil.success();
    }

    @ApiOperation(value = "从已取消订单中重新申请借书", notes = "需要USER角色")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{userId}/orders/{orderId}")
    public Result reapplyBorrowBook(@PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId) throws BookException {
        orderService.reApplyBorrow(userId, orderId);
        return ResultUtil.success();
    }

    @ApiOperation(value = "获取用户借阅情况", notes = "需要USER角色")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{userId}/orders/list")
    public Result listOrder(@PathVariable("userId") Long userId,
                            @RequestParam(value = "status", required = false) OrderStatusEnum orderStatusEnum,
                            @RequestParam(value = "orderId", required = false) Long orderId,
                            @RequestParam(value = "page", defaultValue = "1") Integer page) {
        List<OrderInfoDTO> orderList = orderService.listOrderByPage(userId, orderStatusEnum, orderId, page);
        return ResultUtil.success(orderList);
    }
}
