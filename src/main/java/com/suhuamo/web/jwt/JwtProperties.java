package com.suhuamo.web.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author suhuamo
 * @slogan 想和喜欢的人睡在冬日的暖阳里
 * @date 2023/05/26
 */
@ConfigurationProperties("suhuamo.web.jwt")
@Data
public class JwtProperties {
    /**
     *  签名私钥,默认值 y20160606t
     */
    private String key = "y20160606t";
    /**
     *  签名的有效时间，单位毫秒, 默认60分钟， 1000 * 60 * 60
     */
    private Long ttl = 36000000L;
    /**
     *  作者名，默认值 suhuamo
     */
    private String author = "suhuamo";

    /**
     * 前端请求头中用于存储token认证的属性名称
     */
    private String headerName = "Authorization";
    /**
     * 前端请求头中用于存储token认证的属性对应的内容起始内容，属性完整内容为：{headerStartWith} {token}
     */
    private String headerStartWith = "Bearer";
    /**
     * 当次请求头中截取到的token反转义获得的claims，设置改内容的名称存入request中。
     */
    private String claimsName = "user_claims";

}
