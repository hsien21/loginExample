package com.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.login.service.TuserService;
import com.login.util.AesEncryptUtils;
import com.login.util.SecurityParameter;

@RestController
public class MainController {

	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@Autowired
	TuserService TuserService;

	@Autowired
	AesEncryptUtils aesEncryptUtils;

	@RequestMapping(value = "/test/tuser", method = RequestMethod.GET)
	@SecurityParameter
	public void test() throws Exception {
		log.info("log Test start");
		System.out.println("All iteratorIterable item: ");

		TuserService.tuserTest();

		Map map = new HashMap<String, String>();
		map.put("key", "value");
		map.put("中文", "汉字");
		String content = JSONObject.toJSONString(map);
		System.out.println("加密前：" + content);

		String encrypt = aesEncryptUtils.encrypt(content);
		System.out.println("加密后：" + encrypt);

		String decrypt = aesEncryptUtils.decrypt(encrypt);
		System.out.println("解密后：" + decrypt);

//		return null;
	}
}
