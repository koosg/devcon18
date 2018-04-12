package com.example.hackin.demo.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String eventName;
	private String organizer;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "attendant", joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))

	private Set<User> attendees = new HashSet<>();

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public void addUser(User user) {
		attendees.add(user);
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", eventName=" + eventName + ", organizer=" + organizer + "]";
	}
	
}
