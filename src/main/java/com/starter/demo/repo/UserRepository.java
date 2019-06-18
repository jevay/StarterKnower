package com.starter.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starter.demo.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

	UserInfo findByName(String name);

}
