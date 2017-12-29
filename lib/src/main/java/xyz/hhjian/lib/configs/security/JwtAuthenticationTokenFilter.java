package xyz.hhjian.lib.configs.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.hhjian.lib.utils.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>验证jwt过滤器</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.21
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    public static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private TokenSetting tokenSetting;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(tokenSetting.getTokenHeaderName());
        if (authHeader != null && authHeader.startsWith(tokenSetting.getTokenHead())) {
            final String authToken = authHeader.substring(tokenSetting.getTokenHead().length());
            String username = jwtTokenUtil.getUsernameFromToken(authToken);

            LOGGER.info("checking authentication " + username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken securityToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    securityToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    LOGGER.info("authenticated user " + username + ", setting security context");
                    //在安全容器中加入该token的用户的信息
                    SecurityContextHolder.getContext().setAuthentication(securityToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
