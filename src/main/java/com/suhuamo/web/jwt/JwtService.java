package com.suhuamo.web.jwt;

import com.suhuamo.web.enums.CodeEnum;
import com.suhuamo.web.exception.CustomException;
import io.jsonwebtoken.*;

import java.util.Date;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/04/08
 * 简化获取token数据的代码编写（判断是否登录）
 *   1.通过request获取请求token信息
 *   2.从token中解析获取claims
 *   3.将claims绑定到request域中
 */
public class JwtService {
     private JwtProperties jwtProperties;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 设置认证 token
     *    id:登录用户 id
     *   subject：登录用户名
     * @param id
     * @return String
     */
    public String createJwt(String id) {
        //1.创建失效时间
        long now = System.currentTimeMillis();//当前毫秒
        long exp = now + jwtProperties.getTtl();
        //2.创建jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id) // 设置id
                .setSubject(jwtProperties.getAuthor()) // 设置作者
                .setIssuedAt(new Date()) // 设置签发时间
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getKey()); // 指定签名算法和key值
        // 3.设置失效时间
        jwtBuilder.setExpiration(new Date(exp));
        //4.创建token
        return jwtBuilder.compact();
    }


    /**
     *  解析 token 字符串获取 claims
     * @param token
     * @return Claims
     */
    public Claims parseJwt(String token) throws CustomException {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getKey()) // 设置解析key
                    .parseClaimsJws(token)// 设置需要解析的token
                    .getBody();
            // 输入错误的token值会出现该异常 || 格式错误异常，例如至少两个 - 符号
        } catch (SignatureException | MalformedJwtException e) {
            throw new CustomException(CodeEnum.UNAUTHORIZED_ERROR);
            // token过期异常
        } catch (ExpiredJwtException e) {
            throw new CustomException(CodeEnum.UNAUTHORIZED_EXPIRE);
        }
        return claims;
    }

    /**
     *  判断当前 token 是否过期了，已过期返回 true
     * @param claims
     * @return Boolean
     */
    public Boolean isExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

}