package xyz.hhjian.lib.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

/**
 * <p>json web token 工具类</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.05
 */
public class JwtTokenUtil {

    private static final Long EXPIRATION_MINUTE = 3600L;
    private static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final String CLAIM_VALUE_ISSUER = "linkworld";
    private static String SECRET = "hhjian";

    /**
     * 根据用户信息生成token
     *
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                //该JWT的签发者
                .setIssuer(CLAIM_VALUE_ISSUER)
                //该JWT所面向的用户
                .setSubject(userDetails.getUsername())
                //过期时间
                .setExpiration(generateExpirationDate(EXPIRATION_MINUTE))
                //在什么时候签发的
                .setIssuedAt(new Date())
                .signWith(SIGNATURE_ALGORITHM, SECRET)
                .compact();
    }

    /**
     * 检验token有效性,用户名
     *
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    /**
     * 解析token,获取用户名
     *
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims == null ? null : claims.getSubject();
    }

    /**
     * 从token中获取该token过期时间
     *
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims == null ? null : claims.getExpiration();
    }

    /**
     * 生成过期时间
     *
     * @param expirationMinute
     * @return
     */
    private Date generateExpirationDate(long expirationMinute) {
        return new Date(System.currentTimeMillis() + expirationMinute * 1000);
    }

    /**
     * 校验token是否过期,修改密码后token会过期
     *
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 解析token,获取token的body信息
     *
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
}
