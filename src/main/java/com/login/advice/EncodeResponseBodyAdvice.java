package com.login.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.util.AesEncryptUtils;
import com.login.util.RSAUtils;
import com.login.util.SecurityParameter;

@ControllerAdvice(basePackages = "com.login.advice")
public class EncodeResponseBodyAdvice implements ResponseBodyAdvice {

	private final static Logger logger = LoggerFactory.getLogger(EncodeResponseBodyAdvice.class);

	private static final String CLIENT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9ikrLxa/cgLZXQugBQFhdxCPQmEZ9j9hadra81MqAxmRkc3eFwROAHk/+39fhmDwgtjE/w4cO6XDabL/mi5V37ioByS1QpovF8ZlJgz/RjvV3TEanvxluridXlNTfOd45uC9+TmR2DzRk5p25U1F74wF7K2KSGv2gyqZvttxrfwIDAQAB";

	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		// TODO Auto-generated method stub
		boolean encode = false;
		if (methodParameter.getMethod().isAnnotationPresent(SecurityParameter.class)) {
			// 获取注解配置的包含和去除字段
			SecurityParameter serializedField = methodParameter.getMethodAnnotation(SecurityParameter.class);
			// 出参是否需要加密
			encode = serializedField.outEncode();
		}
		if (encode) {
			logger.info("对方法method :【" + methodParameter.getMethod().getName() + "】返回数据进行加密");
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
				// 生成aes秘钥
				String aseKey = getRandomString(16);
				// rsa加密
				String encrypted = RSAUtils.encryptedDataOnJava(aseKey, CLIENT_PUBLIC_KEY);
				// aes加密
				String requestData = AesEncryptUtils.encrypt(result, aseKey);
				Map<String, String> map = new HashMap<>();
				map.put("encrypted", encrypted);
				map.put("requestData", requestData);
				return map;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(
						"对方法method :【" + methodParameter.getMethod().getName() + "】返回数据进行解密出现异常：" + e.getMessage());
			}
		}
		return body;
	}

	/**
	 * 创建指定位数的随机字符串
	 * 
	 * @param length 表示生成字符串的长度
	 * @return 字符串
	 */
	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

}
