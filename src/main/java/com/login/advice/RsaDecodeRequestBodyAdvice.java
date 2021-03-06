package com.login.advice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RsaDecodeRequestBodyAdvice implements RequestBodyAdvice {

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		// 这里设置成false 它就不会再走这个类了
		return true;
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) throws IOException {

		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		HttpInputMessage testMessage = null;
//		try {
//			testMessage = new MyHttpInputMessage(inputMessage);
//			InputStream inputStream = testMessage.getBody();
//			if (inputStream != null) {
//				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//				char[] charBuffer = new char[128];
//				int bytesRead = -1;
//				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
//					stringBuilder.append(charBuffer, 0, bytesRead);
//				}
//			} else {
//				stringBuilder.append("");
//			}
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		try {
			return new MyHttpInputMessage(inputMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testMessage;

//		/**** 解密 */
//		boolean decode = false;
//		if (parameter.getMethod().isAnnotationPresent(SecurityParameter.class)) {
//			// 获取注解配置的包含和去除字段
//			SecurityParameter serializedField = parameter.getMethodAnnotation(SecurityParameter.class);
//			// 出参是否需要加密
//			decode = serializedField.decode();
//		}
//		if (decode) {
//			// 获取请求数据
//			String builderString = stringBuilder.toString();
//			log.info("【接受的请求数据】", builderString);
//			try {
//				String decodeString = AesEncryptUtils.decrypt(builderString);
//				log.info("【解密后的请求数据】", decodeString);
//				// 把数据放到我们封装的对象中
//				return new MyHttpInputMessage(inputMessage);
//			} catch (Exception e) {
//				e.printStackTrace();
//				return null;
//			}
//		} else {
//			return new MyHttpInputMessage(inputMessage);
//		}

	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		log.info("handleEmptyBody");
		return body;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		log.info("handleEmptyBody");
		return body;
	}

	public class MyHttpInputMessage implements HttpInputMessage {
		private HttpHeaders headers;

		private InputStream body;

		public MyHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
			String data = IOUtils.toString(inputMessage.getBody(), "UTF-8");
			JSONObject json = JSONObject.parseObject(data);
			log.info("data:{}", data);
			this.body = IOUtils.toInputStream(data, "UTF-8");
			this.headers = inputMessage.getHeaders();

		}

		@Override
		public InputStream getBody() throws IOException {
			return body;
		}

		@Override
		public HttpHeaders getHeaders() {
			return headers;
		}
	}

}
