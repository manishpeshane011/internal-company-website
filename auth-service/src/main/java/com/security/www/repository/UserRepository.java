package com.security.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.role.www.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,String> {

	Object findByMobileNumber(String mobileNumber);

}
