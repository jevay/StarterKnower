package com.starter.demo.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starter.demo.entity.UserInfo;
import com.starter.demo.entity.UserSummary;
import com.starter.demo.repo.UserSummaryRepository;
import com.starter.demo.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserInfo userInfo;
	
	private UserSummary userSummary;
	
	@Autowired
	private UserService userService;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UserSummaryRepository usr;
	
	@GetMapping(value="/getUserList")
	public List<UserInfo> getUserList(){
		return userService.getCurrentUserList();
	}
	
	@GetMapping(value="/getUserInfo")
	public UserInfo getUserInfoByName(@RequestParam("name") String name) {
		return userService.getUserByName(name);
	}
	
	@GetMapping(value="/getCurrentUserList")
	public List<UserInfo> getCurrentUserList(){
		return userService.getCurrentUserList();
	}
	
	@GetMapping(value="/getPageUserList")
	public Page<UserInfo> getPageUserList(){
		return userService.getPageUserList();
	}
	
	@PutMapping(value="/addUserInfo")
	public UserInfo addUserInfo(UserInfo userInfo) {
		return userService.addUserInfo(userInfo);
	}
	
	@PostMapping(value="/updateUserInfo")
	public UserInfo updateUserInfo(UserInfo userInfo) {
		return userService.updateUserInfo(userInfo);
	}
	
	@DeleteMapping(value="/delete")
	public void deleteUserInfo(@RequestParam("id") Long id) {
		userService.deleteUserInfoById(id);
	}
	
	@InitBinder
	protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		binder.registerCustomEditor(Date.class, "createTime", new CustomDateEditor(dateFormat, true));
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	@GetMapping(value="/totalSummary")
	public List<UserSummary> getTotalSummary(){
		usr.deleteAll();
		String qlString = "SELECT sum(info.salary) as totalSalary, info.groupName from UserInfo info group by info.groupName";
		Query query = entityManager.createQuery(qlString);
		List<Object[]> resultList = query.getResultList();
		List<UserSummary> summaryList = new ArrayList<>();
		for(Object[] result:resultList) {
			long total = (Long)result[0];
			UserSummary us = userSummary.create((String)result[1], (int)total);
			usr.save(us);
			summaryList.add(us);
		}
		return summaryList;
	}
}
