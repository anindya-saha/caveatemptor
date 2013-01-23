package auction.test;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import auction.controller.WelcomeController;
import auction.dao.BookDAO;
import auction.dao.CategoryDAO;
import auction.dao.UserDAO;
import auction.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/resources/spring/auction-core.xml" })
public class WelcomeControllerUnitTest {
	
	@Autowired
	private WelcomeController welcomeController;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test ended...");
	}

	@Test
	public void test() {		
		welcomeController.save();
	}
}
