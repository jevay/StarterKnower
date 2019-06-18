package com.starter.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.starter.demo.entity.UserInfo;

public interface UserService {
	List<UserInfo> getUserList();
	
	UserInfo getUserByName(String name);
	
	UserInfo addUserInfo(UserInfo userInfo);
	
	UserInfo updateUserInfo(UserInfo userInfo);
	
	void deleteUserInfoById(Long id);
	
	List<UserInfo> getCurrentUserList();
	
	Page<UserInfo> getPageUserList();
	
}
