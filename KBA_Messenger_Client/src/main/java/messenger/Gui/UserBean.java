package messenger.Gui;

import java.io.Serializable;
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

  	
  	public String setUser(User user){
    	this.user = user;
    	return "success";
    }
  	
  	public void refreshUser() {
  		//this.user = userManagement.getUser(this.user);
  		this.user = userManagement.getUserById(this.user.getUserId());
  		/*this.init();
  		for(User u : this.userList) {
  			if(u.getUserId()==this.user.getUserId()) {
  				this.user = u;
  			}
  		}*/
  	}
  	
  	public String deleteUser(User user) {
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

    
}
