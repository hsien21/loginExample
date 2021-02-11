package com.login.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name= "t_user")
public class TUser {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int age;
	private String type;
	
	
	
}
