package auction.controller;

import org.springframework.context.annotation.Bean;

import auction.model.User;

public interface WelcomeController {
	
	public boolean validateLogin(User user);
	
	public void save();
}
