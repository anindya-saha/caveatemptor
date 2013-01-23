package auction.dao;

import auction.model.User;

public interface UserDAO extends GenericDAO<User, Long> { 
	 public boolean validateLogin(User user);
}
