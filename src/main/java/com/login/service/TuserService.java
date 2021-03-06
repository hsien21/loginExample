package com.login.service;

import java.util.List;

import com.login.entity.TUser;
import com.login.entity.UserAccount;

public interface TuserService {

	public List<TUser> tuserTest();

	public Object getUserInfoByLoginName(String username);

	Object getLoginUser(UserAccount userAccount);

}
