package xyz.hhjian.lib.controller.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.hhjian.lib.entity.bo.LoginBO;
import xyz.hhjian.lib.entity.bo.RegisterBO;
import xyz.hhjian.lib.entity.domain.User;
import xyz.hhjian.lib.entity.dto.Result;
import xyz.hhjian.lib.entity.dto.TokenInfoDTO;
import xyz.hhjian.lib.exception.ValidateException;
import xyz.hhjian.lib.service.AuthService;
import xyz.hhjian.lib.utils.ResultUtil;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>授权 rest 控制器</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.05
 */
@Api(tags = "用户授权验证")
@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation(value = "用户注册", notes = "用户注册接口,可以无授权访问")
    @ApiImplicitParam(name = "Authorization", value = "不用填", required = false, paramType = "header")
    @PostMapping("${security.jwt.route.authentication.register}")
    public Result register(@Valid @RequestBody RegisterBO registerUser, BindingResult bindingResult) throws ValidateException {
        //服务端校验
        List<ObjectError> errors = bindingResult.getAllErrors();
        if (bindingResult.hasErrors()) {
            throw new ValidateException(errors.get(0).getDefaultMessage());
        }
        User user = new User();
        BeanUtils.copyProperties(registerUser, user);
        authService.register(user);
        return ResultUtil.success();
    }

    @ApiOperation(value = "用户登录", notes = "登录成功后返回token信息")
    @ApiImplicitParam(name = "Authorization", value = "不用填", required = false, paramType = "header")
    @PostMapping("${security.jwt.route.authentication.login}")
    public Result login(@Valid @RequestBody LoginBO login, BindingResult bindingResult) throws ValidateException {
        List<ObjectError> errors = bindingResult.getAllErrors();
        if (bindingResult.hasErrors()) {
            throw new ValidateException(errors.get(0).getDefaultMessage());
        }
        TokenInfoDTO tokenInfoDTO = authService.login(login.getUsername(), login.getPassword());
        return ResultUtil.success(tokenInfoDTO);
    }
}
