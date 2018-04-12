package com.example.hackin.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.example.hackin.demo.domain.Event;
import com.example.hackin.demo.domain.User;
import com.example.hackin.demo.repository.EventRepository;
import com.example.hackin.demo.repository.UserRepository;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {

		User user = new User("John Doe", "jdoe", encoder.encode("secret"));
		user = userRepo.save(user);
		User user2 = userRepo.findByUsername("jdoe");

		Event event = new Event();
		event.setEventName("Luminis Devcon");
		event.setOrganizer("Luminis");
		event.addUser(user2);
		eventRepo.save(event);

		
		Event event2 = new Event();
		event2.setEventName("JFall 2018");
		event2.setOrganizer("NL-JUG");
		eventRepo.save(event2);
	}

}
