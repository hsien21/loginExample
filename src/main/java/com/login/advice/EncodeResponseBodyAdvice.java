package com.login.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.util.AesEncryptUtils;
import com.login.util.SecurityParameter;

import lombok.extern.slf4j.Slf4j;

/**
 * 返回数据加密
 * 
 */
@SuppressWarnings("rawtypes")
@Slf4j
@RestControllerAdvice
public class EncodeResponseBodyAdvice implements ResponseBodyAdvice {

	@Override
	public boolean supports(MethodParameter methodParameter, Class aClass) {
		log.info("有執行");
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass,
			ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
		log.info("有執行beforeBodyWrite");
		boolean encode = false;
		if (methodParameter.getMethod().isAnnotationPresent(SecurityParameter.class)) {
			// 获取注解配置的包含和去除字段
			SecurityParameter serializedField = methodParameter.getMethodAnnotation(SecurityParameter.class);
			// 出参是否需要加密
			encode = serializedField.encode();
		}
		/**
		 * 加密开始
		 */
		if (encode) {
			log.info("对接口名为【" + methodParameter.getMethod().getName() + "】返回数据进行加密");
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
				log.info("加密返回数据 :【" + AesEncryptUtils.encrypt(result) + "】");
				return AesEncryptUtils.encrypt(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return body;
	}

}
