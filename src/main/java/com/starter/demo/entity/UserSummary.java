package com.starter.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_summary")
public class UserSummary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="group_name")
	private String groupName;
	
	@Column(name="total_salary")
	private int totalSalary;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public int getTotalSalary() {
		return totalSalary;
	}
	public void setTotalSalary(int totalSalary) {
		this.totalSalary = totalSalary;
	}
	
	public static UserSummary create(String groupName, int totalSalary) {
		UserSummary us = new UserSummary();
		us.setGroupName(groupName);
		us.setTotalSalary(totalSalary);
		
		return us;
	}
	
	@Override
	public String toString() {
		return "{"
				+ "id: " + this.id + ","
				+ "groupName: " + this.groupName + ","
				+ "totalSalary: " + this.totalSalary
				+ "}";
	}
	
}
