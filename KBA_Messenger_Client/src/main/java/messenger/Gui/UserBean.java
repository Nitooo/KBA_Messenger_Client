package messenger.Gui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import messenger.Domain.User;
import messenger.ServiceAdapter.GetUserAdapter;
import messenger.ServiceAdapter.UserManagementAdapter;

import org.springframework.context.annotation.ScopedProxyMode;

@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
    private UserManagementAdapter userManagement;
    
	@Autowired
    private GetUserAdapter getUser;
    
    private List<User> userList;
    
	private User user;

	@PostConstruct
    public void init() {
    	userList = Arrays.asList(userManagement.getAllUsers());
    }

    
    public String save(User user){
    	//user = userManagement.getUser(user);
    	this.user = user;
    	return "success";
    }
    
    public String deleteUser(){
    	userManagement.deleteUser(user);
    	this.logout();
    	this.init();
    	return "successDelete";
    }
    
    public String logout(){
    	FacesContext
		.getCurrentInstance()
		.getExternalContext()
		.invalidateSession();
    	return "chooseUser?faces-redirect=true";
    }

    public UserManagementAdapter getUserManagement() {
  		return userManagement;
  	}


  	public void setUserManagement(UserManagementAdapter userManagement) {
  		this.userManagement = userManagement;
  	}


  	public GetUserAdapter getGetUser() {
  		return getUser;
  	}


  	public void setGetUser(GetUserAdapter getUser) {
  		this.getUser = getUser;
  	}


  	public List<User> getUserList() {
  		return userList;
  	}


  	public void setUserList(List<User> userList) {
  		this.userList = userList;
  	}


  	public User getUser() {
  		return user;
  	}


  	public void setUser(User user) {
  		this.user = user;
  	}
  	
  	public void addUser(String username, String passwort) {
  		userManagement.addUser(username, passwort);
  	}

    
}
