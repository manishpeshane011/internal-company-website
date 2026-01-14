package com.security.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,String> {

	Object findByMobileNumber(String mobileNumber);

}
