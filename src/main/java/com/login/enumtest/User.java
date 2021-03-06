package com.login.enumtest;

public enum User {
	COMMON("commonUser", 0), ADVANCE("advanceUser", 1);

	private String userTypeString;
	private int num;

	User(String userTypeString, int num) {
		// TODO Auto-generated constructor stub
		this.num = num;
		this.userTypeString = userTypeString;
	}

	public String getUserTypeString() {
		return userTypeString;
	}

	public int getNum() {
		return num;
	}

}
