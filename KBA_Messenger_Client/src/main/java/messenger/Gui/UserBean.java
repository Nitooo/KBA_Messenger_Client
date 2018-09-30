package messenger.Gui;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import messenger.Domain.User;
import messenger.Service.ManageContactList;
import messenger.ServiceAdapter.GetUserAdapter;
import messenger.ServiceAdapter.UserManagementAdapter;

import org.springframework.context.annotation.ScopedProxyMode;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserManagementAdapter userManagement;

	@Autowired
	private GetUserAdapter getUser;

	@Autowired
	private ManageContactList manageContactList;

	private ErrorMessagesGui errorMessages = new ErrorMessagesGui();

	private List<User> userList;

	private User user;

	@PostConstruct
	public void init() {
		try {
			userList = Arrays.asList(userManagement.getAllUsers());
		} catch (ResourceAccessException e) {
			errorMessages.error("Verbindung mit Server ist nicht möglich!");
		} catch (Exception e) {
			errorMessages.error("Es ist ein kritischer Fehler aufgetreten!");
		}

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

	public String setUser(User user) {
		this.user = user;
		return "success";
	}
	
	/**
	 * get up-to-date version of User
	 */
	public void refreshUser() {
		this.user = userManagement.getUserById(this.user.getUserId());
	}
	
	/**
	 * deletes user
	 * 
	 * @return is used for navigation(faces-config.xml)
	 */
	public String deleteUser(User user) {
		
		try {
			manageContactList.deleteUserFromContacs(user);
			boolean status = userManagement.deleteUser(user);
			if(status) {
				this.logout();
				this.init();
				return "successDeleteUser";
			} else {
				errorMessages.error("Beim löschen des Users ist ein Fehler aufgetreten!");
				return "error";
			}
			
		} catch (Exception e) {
			errorMessages.error("Es ist ein kritischer Fehler aufgetreten!");
			return "error";
		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "chooseUser?faces-redirect=true";
	}

}
