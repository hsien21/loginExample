package com.login.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.login.entity.GetTokenRequest;
import com.login.entity.Response;
import com.login.entity.TUser;
import com.login.service.TuserService;
import com.login.util.JwtTokenUtil;
import com.login.util.SecurityParameter;

@RestController
@RequestMapping(value = "/user")
public class MainController {

	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@Autowired
	TuserService TuserService;
	@Autowired
	JwtTokenUtil jwtTokenUtil;

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

	@RequestMapping(value = "/login2", method = RequestMethod.POST)
	public TUser toLogin(@RequestBody TUser tuser) {
		String username = jwtTokenUtil.getTokenClaimsSubject();
		log.info("username:{}", username);
		TUser user1 = (TUser) TuserService.getUserInfoByLoginName(username);

		return user1;
	}

	@RequestMapping(value = "/loginByGet", method = RequestMethod.GET)
	@SecurityParameter(encode = false)
	public TUser LoginByGet(@RequestParam(name = "id") Long id) {

		log.info(jwtTokenUtil.getHttpToken());

		return null;
	}

	@RequestMapping(value = "/getToken", method = RequestMethod.POST)
	@SecurityParameter(encode = false)
	public Response token(@RequestBody GetTokenRequest getTokenRequest) {

		return TuserService.getLoginUser(getTokenRequest);
	}

}
