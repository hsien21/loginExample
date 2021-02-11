package com.login.repository;




import org.springframework.data.repository.CrudRepository;

import com.login.entity.TUser;



public interface TUserRepository extends CrudRepository<TUser,Long> {
	

}
