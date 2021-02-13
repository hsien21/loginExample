package com.login.servicelmp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.entity.TUser;
import com.login.repository.TUserRepository;
import com.login.service.TuserService;

@Service
public class TuserServiceImp implements TuserService {

	private static final Logger Logger = LoggerFactory.getLogger(TuserServiceImp.class);

	@Autowired
	TUserRepository tUserRepository;

	@Override
	public List<TUser> tuserTest() {
		// TODO Auto-generated method stub
		List<TUser> testList = (List<TUser>) tUserRepository.findAll();
		System.out.println(testList.size());

		testList.forEach(item -> System.out.println(item.toString()));
		Logger.info("TuserService");

		return (List<TUser>) tUserRepository.findAll();

	}

}
