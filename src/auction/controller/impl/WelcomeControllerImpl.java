package auction.controller.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import auction.controller.WelcomeController;
import auction.dao.BookDAO;
import auction.dao.CategoryDAO;
import auction.dao.UserDAO;
import auction.model.Book;
import auction.model.Category;
import auction.model.User;

public class WelcomeControllerImpl implements WelcomeController {

	private UserDAO userDAO;
	
	private BookDAO bookDAO;
	
	private CategoryDAO categoryDAO;
	
	@Transactional(readOnly = true)
	public boolean validateLogin(User user) {
		return userDAO.validateLogin(user);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void save() {
		/*User user = new User();
		user.setUsername("anie");
		user.setPassword("anie");
		
		userDAO.persist(user);*/
		
		Book book = new Book("title2", "author2");
		bookDAO.persist(book);
		
		Category category = new Category("cat2");
		categoryDAO.persist(category);
		
		category.addBook(book);
		categoryDAO.merge(category);
		
/*		Book book = bookDAO.findById(10l, false);
		System.out.println(book.getCategories());
		
		Book book3 = new Book("title3", "author3");
		bookDAO.persist(book3);
		book.getCategories().iterator().next().addBook(book3);*/
		
/*		categoryDAO.findById(10l, false).removeBook(bookDAO.findById(15l, false));
		for(Category category : categoryDAO.findAll()) {
			System.out.println(category.getBooks().size());
		}*/
		
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}	
}
