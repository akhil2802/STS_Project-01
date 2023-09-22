package com.emailverification.event;

import org.springframework.context.ApplicationEvent;

import com.emailverification.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	private String applicationUrl;
	
	public RegistrationCompleteEvent(User user, String applicationUrl) {
		super(user);
		this.user = user;
		this.applicationUrl = applicationUrl;
	}
	
	
}
