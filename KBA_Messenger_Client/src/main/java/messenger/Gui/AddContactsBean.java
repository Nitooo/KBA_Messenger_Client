package messenger.Gui;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import messenger.Domain.User;
import messenger.Service.ManageContactList;
import messenger.ServiceAdapter.GetUserAdapter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AddContactsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserBean userBean;

	@Autowired
	private GetUserAdapter getUser;

	@Autowired
	private ManageContactList manageContactList;
	
	ErrorMessagesGui errorMessages = new ErrorMessagesGui();

	private String contactName;

	@PostConstruct
	private void init() {
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public GetUserAdapter getGetUser() {
		return getUser;
	}

	public void setGetUser(GetUserAdapter getUser) {
		this.getUser = getUser;
	}

	public ManageContactList getManageContactList() {
		return manageContactList;
	}

	public void setManageContactList(ManageContactList manageContactList) {
		this.manageContactList = manageContactList;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String addContact() {
		if (!this.contactName.equals(userBean.getUser().getUsername())) {
			
			User contact = getUser.getUser(this.contactName);
			if (contact != null) {
					manageContactList.addContact(userBean.getUser(), contact);
					userBean.refreshUser();
					return "successAddContact";
			}else {
				errorMessages.warn("User " + this.contactName + " wurde nicht gefunden!");
				return "success";
			}
			
		} else {
			errorMessages.warn("Sie können sich nicht selbst als User hinzufügen!");
			return "success";
		}

	}

}