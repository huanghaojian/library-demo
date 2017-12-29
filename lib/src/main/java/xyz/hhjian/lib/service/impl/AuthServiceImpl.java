package xyz.hhjian.lib.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hhjian.lib.configs.security.JwtUserDetails;
import xyz.hhjian.lib.entity.domain.User;
import xyz.hhjian.lib.entity.dto.TokenInfoDTO;
import xyz.hhjian.lib.entity.enums.RoleEnum;
import xyz.hhjian.lib.mapper.UserMapper;
import xyz.hhjian.lib.service.AuthService;
import xyz.hhjian.lib.utils.JwtTokenUtil;

/**
 * <p>授权 业务层</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.05
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserMapper userMapper;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userMapper = userMapper;
    }

    /**
     * 用户登录
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(User user) {
        if (userMapper.selectById(user.getUsername()) != null) {
            return;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleEnum.USER);
        userMapper.insert(user);
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public TokenInfoDTO login(String username, String password) {
        //向security容器加入登录信息
        UsernamePasswordAuthenticationToken loginToken =
                new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(loginToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成Token
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        JwtUserDetails jwtUserDetails = (JwtUserDetails) userDetails;
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        TokenInfoDTO tokenInfoDTO = new TokenInfoDTO();
        tokenInfoDTO.setUserId(jwtUserDetails.getUser().getUserId());
        tokenInfoDTO.setToken(jwtToken);
        return tokenInfoDTO;
    }
}
