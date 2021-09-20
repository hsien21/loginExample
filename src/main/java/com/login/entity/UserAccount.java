package com.login.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "useraccount")
public class UserAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String account;
	private String password;
	private String username;

	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", account=" + account + ", password=" + password + ", username=" + username
				+ "]";
	}

}
