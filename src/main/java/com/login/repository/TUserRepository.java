package com.login.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.login.entity.TUser;

public interface TUserRepository extends CrudRepository<TUser, Long> {
	@Query(value = "select u from TUser u where u.name=:username")
	TUser findByTUserId(@Param("username") String username);

	@Query(value = "select u from TUser u left join UserAccount account on u.name = account.username where account.account=:userAccount and account.password=:password")
	Object findByUserAccount(@Param("userAccount") String userAccount, @Param("password") String password);

}
