package com.login.demo.servicelmp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.login.service.TuserService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class tuserServicelmpTests {

	@Autowired
	TuserService TuserService;

	@BeforeEach
	public void init() {
		System.out.println("开始测试-----------------");
	}

	@AfterEach
	public void after() {
		System.out.println("测试结束-----------------");
	}

	@Test
	void contextLoads() {
		TuserService.tuserTest();
	}

}
