package messenger.Gui;

import java.io.Serializable;
import java.util.Set;

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
public class ContactsBean implements Serializable {

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

	private Set<User> contactList;

	private String contactName;

	@PostConstruct
	public void init() {
		this.setContactList(manageContactList.getContactList(userBean.getUser()));
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

	public Set<User> getContactList() {
		return contactList;
	}

	public void setContactList(Set<User> contactList) {
		this.contactList = contactList;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String deleteContact(User contact) {
		manageContactList.deleteContact(userBean.getUser(), contact);
		userBean.refreshUser();
		return "reset";
	}

}