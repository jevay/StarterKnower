package com.starter.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.starter.demo.entity.UserInfo;
import com.starter.demo.repo.UserRepository;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<UserInfo> getUserList() {
		return userRepo.findAll();
	}

	@Override
	public UserInfo getUserByName(String name) {
		return userRepo.findByName(name);
	}

	@Override
	public UserInfo addUserInfo(UserInfo userInfo) {
		return userRepo.save(userInfo);
	}

	@Override
	public UserInfo updateUserInfo(UserInfo userInfo) {
		return userRepo.save(userInfo);
	}

	@Override
	public void deleteUserInfoById(Long id) {
		UserInfo userinfo = userRepo.getOne(id);
		userRepo.delete(userinfo);
	}

	@Override
	public List<UserInfo> getCurrentUserList() {
		Sort sort = new Sort(Sort.Direction.DESC, "createTime");
		return userRepo.findAll(sort);
	}

	@Override
	public Page<UserInfo> getPageUserList() {
		Sort sort = new Sort(Sort.Direction.DESC, "createTime");
		Pageable pageable = PageRequest.of(0, 5, sort);
		return userRepo.findAll(pageable);
	}

}
