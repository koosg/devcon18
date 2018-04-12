package com.example.hackin.demo;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.hackin.demo.domain.User;
import com.example.hackin.demo.repository.UserRepository;

@Component
public class UserResolver {

	private final String USER_REQUEST_ATTRIBUTE = "USER-REQ";

	@Autowired
	private UserRepository userRepository;

	public Optional<User> resolveUser(HttpServletRequest req) {
		User requestUser = (User) req.getAttribute(USER_REQUEST_ATTRIBUTE);
		if (requestUser != null) {
			return Optional.of(requestUser);
		}
		Optional<User> contextUser = resolveUserFromSecurityContext();
		contextUser.ifPresent(user -> req.setAttribute(USER_REQUEST_ATTRIBUTE, user));
		return contextUser;
	}


	private Optional<User> resolveUserFromSecurityContext() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
		if (authentication == null) {
			return Optional.empty();
		}
		String username = authentication.getName();
		User user = userRepository.findByUsername(username);
		return Optional.ofNullable(user);
	}

}
