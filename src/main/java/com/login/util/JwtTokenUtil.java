package com.login.util;

import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.jwt.bean.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
@Slf4j
public class JwtTokenUtil {
	@Autowired
	private JwtProperties jwtProperties;

	public JwtProperties getJwtProperties() {
		return jwtProperties;
	}

	public String getSecretKey() {
		return Base64.getEncoder().encodeToString(jwtProperties.getSecret().getBytes());
	}

	/**
	 * 生成token
	 * 
	 * @param subject 一般为用户名
	 * @return
	 */
	public String createToken(Object subject) {
		String payload = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			log.info("subject:{}", subject);
			String secret = jwtProperties.getSecret();
			long expire = jwtProperties.getExpire();
			String issuer = jwtProperties.getIssuer();

			payload = objectMapper.writeValueAsString(subject);
			Date nowDate = new Date();
			Date expireDate = new Date(nowDate.getTime() + expire * 1000);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Jwts.builder().setPayload(payload).setHeaderParam("type", "JWT")
				.signWith(SignatureAlgorithm.HS256, getSecretKey()).compact();

	}

	/**
	 * 获取token中注册信息 (body信息)
	 * 
	 * @param token
	 * @return
	 */
	public Claims parseJWT(String token) {
		try {
			if (StringUtils.isEmpty(token)) {
				throw new Exception("token不能为空");
			}

			String[] header = token.split("Bearer");
			token = header[1];

			String secret = getSecretKey();
			return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 从http请求中获取http的token头
	 * 
	 * @return
	 */
	public String getHttpToken() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String token = request.getHeader(jwtProperties.getHeader());

		return token;
	}

	public Claims getTokenClaims() {
		String token = getHttpToken();
		return parseJWT(token);
	}

	public String getTokenClaimsSubject() {
		String token = getHttpToken();
		log.info("token:{}", token);
		Claims claims = parseJWT(token);
		log.info("claims.getSubject():{}", claims.getSubject());
		log.info("claims:{}", claims);
		return claims.getSubject();

	}

}
