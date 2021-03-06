package com.login.user;

public class CommonUser implements User {

	@Override
	public String createUser() {
		// TODO Auto-generated method stub
		System.out.println("一般使用者");
		return "一般使用者";
	}

}
