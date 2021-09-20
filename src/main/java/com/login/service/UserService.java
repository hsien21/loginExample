package com.login.service;

import java.util.List;

import com.login.entity.User;

public interface UserService {
	User getUser(Integer id);

	List<User> getUserList();

	User updateUser(Integer id);

	void clearUserCache(Integer id);

	void clearAllCache();
}
