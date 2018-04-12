package com.example.hackin.demo.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String passwordHash;
	private String username;
	private String fullname;
	@ManyToMany(mappedBy = "attendees")
	private Set<Event> events;

	// for JPA/hibernate
	protected User() {

	}

	public User( String fullname,String username, String passwordHash) {
		this.passwordHash = passwordHash;
		this.username = username;
		this.fullname = fullname;
	}

	public void addEvent(Event e) {
		events.add(e);
		e.addUser(this);
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String newName) {
		this.fullname = newName;
	}


	public List<String> getEventNames() {
		return events.stream()
				.map(e -> e.getEventName()).collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", passwordHash=" + passwordHash + ", username=" + username + ", fullname=" + fullname
				+ ", events=" + events + "]";
	}

}
