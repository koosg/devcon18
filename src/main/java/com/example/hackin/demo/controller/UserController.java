package com.example.hackin.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hackin.demo.UserResolver;
import com.example.hackin.demo.controller.domain.Greeter;
import com.example.hackin.demo.domain.Event;
import com.example.hackin.demo.domain.User;
import com.example.hackin.demo.repository.EventRepository;
import com.example.hackin.demo.repository.UserRepository;

@Controller
@RequestMapping({"/" })
public class UserController {

	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private UserResolver resolver;
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/")
	public String showEvents(Model model, @ModelAttribute User user) {
		model.addAttribute("possibleEvents", eventRepo.findAll());
		return "/home";
	}
	

	@PostMapping("/addEvent")
	public String attendsEvent(@RequestParam("eventId") Integer eventId, @ModelAttribute User user) {
		Optional<Event> event = eventRepo.findById((long) eventId);
		event.ifPresent(ev -> {
			user.addEvent(ev);
			userRepo.save(user);
		});
		return "redirect:/";
	}

	@GetMapping("/show")
	public ResponseEntity<String> showUser(@ModelAttribute User user) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(org.springframework.http.MediaType.TEXT_PLAIN);
		return new ResponseEntity<String>(user.toString(), httpHeaders, HttpStatus.OK);
	}

	@ModelAttribute
	public User getUserFromDatabase(HttpServletRequest req) {
		return resolver.resolveUser(req).get();
	}
	
	@ModelAttribute
	public Greeter getGreeterMessage() {
		return new Greeter("Hello everyone at Luminis Devcon!");
	}
	
}
