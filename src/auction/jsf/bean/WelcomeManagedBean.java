package auction.jsf.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

import org.springframework.transaction.annotation.Transactional;

import auction.controller.WelcomeController;
import auction.model.User;

@ManagedBean(name = "WelcomeManagedBean")
@SessionScoped
public class WelcomeManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	
	@NotNull(message = "Please enter username")
	private String username = "ani";
	
	@NotNull(message = "Please enter password")
    //@Pattern(regexp = ".*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*", message = "Password is not strong enough")
	private String password = "ani";
	
	@ManagedProperty(value = "#{welcomeController}")
	private WelcomeController welcomeController;
	
	public WelcomeManagedBean() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("WelcomeManagedBean");
	}
	
	@Transactional(readOnly = true)
	public String login(){
		User user = new User();
		user.setUsername(getUsername());
		user.setPassword(getPassword());		
		
		return welcomeController.validateLogin(user) == true ? SUCCESS : ERROR;
	}
	
	public void reset() {
		this.setPassword("");
		this.setUsername("");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public WelcomeController getWelcomeController() {
		return welcomeController;
	}

	public void setWelcomeController(WelcomeController welcomeController) {
		this.welcomeController = welcomeController;
	}
	
}
