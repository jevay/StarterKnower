package com.starter.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.starter.demo.entity.UserSummary;
import com.starter.demo.repo.UserSummaryRepository;

public class UserSummaryServiceImp implements UserSummaryService {
	
	@Autowired
	UserSummaryRepository userSummaryRepo;
	
	@Override
	public UserSummary saveUserSummary(UserSummary userSummary) {
		
		return userSummaryRepo.save(userSummary);
	}

}
