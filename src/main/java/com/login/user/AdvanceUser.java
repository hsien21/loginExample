package com.login.user;

public class AdvanceUser implements User {

	@Override
	public String createUser() {
		// TODO Auto-generated method stub
		System.out.println("高級使用者");
		return "高級使用者";
	}

}
