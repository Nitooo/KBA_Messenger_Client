package messenger.Gui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import messenger.ServiceAdapter.GetUserAdapter;
import messenger.ServiceAdapter.ManageContactListAdapter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AddContactsBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserBean userBean;

	@Autowired
	private GetUserAdapter getUser;
	
	@Autowired
	private ManageContactListAdapter manageContactList;
	
	private String contactName;
	
	@PostConstruct
	private void init(){
		//contactsIdList = manageContactList.getContactList(userBean.getUserId());
		//contactsList.add(new GuiContact(30L, "DummyContact"));
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



	public ManageContactListAdapter getManageContactList() {
		return manageContactList;
	}



	public void setManageContactList(ManageContactListAdapter manageContactList) {
		this.manageContactList = manageContactList;
	}



	public String getContactName() {
		return contactName;
	}



	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	


	public String addContact() {
		manageContactList.addContact(userBean.getUser(),getUser.getUser(this.contactName));
		return "success";
	}

}