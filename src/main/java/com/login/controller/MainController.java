package com.login.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.login.entity.TUser;
import com.login.service.TuserService;
import com.login.util.SecurityParameter;

@RestController
public class MainController {

	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@Autowired
	TuserService TuserService;

	@Value("${eureka.instance.instance-id}")
	private String instanceId;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/test/tuser", method = RequestMethod.GET)
	@SecurityParameter(encode = false)
	public List<TUser> test() throws Exception {
		log.info(instanceId);
		System.out.println("All iteratorIterable item: ");

		List<TUser> tuserList = TuserService.tuserTest();

		return tuserList;
	}
}
