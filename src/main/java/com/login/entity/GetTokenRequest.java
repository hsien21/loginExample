package com.login.entity;

import lombok.Data;

@Data
public class GetTokenRequest {
	private String account;
	private String password;
}
