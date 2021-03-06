package com.lifemeds.admin.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifemeds.admin.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByUserName(String uname);
	
	public User findByUserNameAndIsAdmin(String uname, boolean isAdmin);
	
	public User findByUserNameAndUserPasswordAndIsAdmin(String uname, String password, boolean isAdmin);
}
