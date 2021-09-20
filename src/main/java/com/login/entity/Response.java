package com.login.entity;

import lombok.Data;

@Data
public class Response {
	private String errorCode;
	private String errorMessage;
	private Object Result;
	private Boolean status;

}
