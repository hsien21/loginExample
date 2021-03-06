package com.login.jwt.bean;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
	private String secret;// 加密密钥
	private Long expire; // token有效期 秒
	private String header;// token 采用的http头，一般使用: Authorization
	private String issuer;// 发行人

	private List<String> ignores; // 忽略token的页面

}
