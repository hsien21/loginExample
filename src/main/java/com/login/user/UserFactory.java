package com.login.user;

public class UserFactory {

	public User userFactory(String userType) {
		switch (userType) {
		case "common":

			return new CommonUser();
		case "advance":

			return new AdvanceUser();
		default:
			break;
		}
		return null;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserFactory userFactory = new UserFactory();
		User commonUser = userFactory.userFactory("common");
		commonUser.createUser();

	}

}
