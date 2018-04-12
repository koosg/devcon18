package com.example.hackin.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hackin.demo.domain.Event;
import com.example.hackin.demo.domain.User;

public interface EventRepository extends JpaRepository<Event, Long>{

	List<Event> findByAttendeesContaining(User user);
}
