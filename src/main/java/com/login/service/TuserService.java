package com.login.service;

import java.util.List;

import com.login.entity.GetTokenRequest;
import com.login.entity.Response;
import com.login.entity.TUser;

public interface TuserService {

	public List<TUser> tuserTest();

	public Object getUserInfoByLoginName(String username);

	Response getLoginUser(GetTokenRequest getTokenRequest);

}
