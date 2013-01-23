package auction.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import auction.dao.BookDAO;
import auction.dao.CategoryDAO;
import auction.dao.UserDAO;
import auction.model.User;

@ContextConfiguration({ "/resources/spring/auction-core.xml" })
public class UnitTest {
	
	@Autowired
	private static UserDAO userDAO;
	
	@Autowired
	private static BookDAO bookDAO;
	
	@Autowired
	private static CategoryDAO categoryDAO;

	@Test
	public void testUser() {
		
		User user = new User();
		user.setUsername("ani");
		user.setPassword("ani");
		
		userDAO.persist(user);
		
		//User userFromDb = userDAO.findByExample(user).get(0);
		//Assert.assertNotNull(userFromDb);
	}
}
