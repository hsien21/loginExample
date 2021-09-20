package com.login.servicelmp;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.login.entity.User;
import com.login.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	@Cacheable(cacheNames = "user", keyGenerator = "KeyGenerator")
	public User getUser(Integer id) {
		User user = null;
		if (id == 1) {
			user = new User(1, "张一 key 1");
		} else if (id == 2) {
			user = new User(2, "张二 key 2");
		} else if (id == 3) {
			user = new User(3, "张三 key 3");
		}
		return user;
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearUserCache(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearAllCache() {
		// TODO Auto-generated method stub

	}

}
