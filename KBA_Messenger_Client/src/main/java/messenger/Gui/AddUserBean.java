package messenger.Gui;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import messenger.ServiceAdapter.UserManagementAdapter;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AddUserBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private String username;
	
	private String password;
	
	@Autowired
	private UserBean userBean;

	@Autowired
	private UserManagementAdapter userManagement;
	

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



	public UserBean getUserBean() {
		return userBean;
	}



	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}



	public UserManagementAdapter getUserManagement() {
		return userManagement;
	}



	public void setUserManagement(UserManagementAdapter userManagement) {
		this.userManagement = userManagement;
	}
	
	public String save() {
		
		if(username != null && password != null) 
		{
			int result = userManagement.addUser(username, password);
			if(result == 0){
				userBean.init();
				return "success";
			} else {
				return "fail";
			}
			 
		} else {
			return "fail";
		}
		
	}

}