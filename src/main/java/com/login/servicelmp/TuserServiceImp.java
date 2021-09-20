package com.login.servicelmp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.entity.GetTokenRequest;
import com.login.entity.Response;
import com.login.entity.TUser;
import com.login.repository.TUserRepository;
import com.login.service.TuserService;
import com.login.util.JwtTokenUtil;

@Service
public class TuserServiceImp implements TuserService {

	private static final Logger Logger = LoggerFactory.getLogger(TuserServiceImp.class);

	@Autowired
	TUserRepository tUserRepository;
	@Autowired
	JwtTokenUtil JwtTokenUtil;

	@Override
	public List<TUser> tuserTest() {
		// TODO Auto-generated method stub
		List<TUser> testList = (List<TUser>) tUserRepository.findAll();
		System.out.println(testList.size());

		testList.forEach(item -> System.out.println(item.toString()));
		Logger.info("TuserService");

		return (List<TUser>) tUserRepository.findAll();

	}

	@Override
	public Object getUserInfoByLoginName(String username) {
		// TODO Auto-generated method stub
		Object tUser = tUserRepository.findByTUserId("jack");
		System.out.println(tUser.toString());
		return tUser;
	}

	@Override
	public Response getLoginUser(GetTokenRequest getTokenRequest) {
		Response response = new Response();
		String jwtTokenString = null;
		TUser tuser = tUserRepository.findByUserAccount(getTokenRequest.getAccount(), getTokenRequest.getPassword());
		if (tuser != null) {
			jwtTokenString = JwtTokenUtil.createToken(tuser.getId());
			Map<String, Object> userResponse = new HashMap();
			userResponse.put("jwtToken", jwtTokenString);
			userResponse.put("usrImfo", tuser);
			response.setResult(userResponse);
		}

		return response;
	}

}
