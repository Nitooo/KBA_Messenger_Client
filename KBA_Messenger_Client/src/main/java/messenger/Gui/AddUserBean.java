package messenger.Gui;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import messenger.ServiceAdapter.UserManagementAdapter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AddUserBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	@Autowired
	private UserBean userBean;

	@Autowired
	private UserManagementAdapter userManagement;

	ErrorMessagesGui errorMessages = new ErrorMessagesGui();

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

	/**
	 * adds User
	 * 
	 * @return is used for navigation(faces-config.xml)
	 */
	public String addUser() {

		try {
			int status = userManagement.addUser(username, password);
			if (status == 0) {
				userBean.init();
				return "success";
			} else if (status == 1) {
				errorMessages.warn("User existiert bereits!");
				return "error";
			} else {
				errorMessages.error("Ein Fehler ist Aufgetreten, bitte kontaktiern Sie den Admin!");
				return "error";
			}
		} catch (Exception e) {
			errorMessages.error("Es ist ein kritischer Fehler aufgetreten!");
			return "error";
		}
	}

}