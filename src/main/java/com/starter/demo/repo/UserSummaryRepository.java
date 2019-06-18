package com.starter.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starter.demo.entity.UserSummary;

public interface UserSummaryRepository extends JpaRepository<UserSummary, Long> {

}
