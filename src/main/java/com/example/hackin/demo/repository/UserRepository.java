package com.example.hackin.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hackin.demo.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);

}
