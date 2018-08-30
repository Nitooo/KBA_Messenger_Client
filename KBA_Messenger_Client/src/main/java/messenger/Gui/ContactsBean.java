package messenger.Gui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import messenger.ServiceAdapter.GetUserAdapter;
import messenger.ServiceAdapter.ManageContactListAdapter;

@Component
@Scope("prototype")
public class ContactsBean implements Serializable{

	
	@Autowired
	private UserBean userBean;

	@Autowired
	private GetUserAdapter getUser;
	
	@Autowired
	private ManageContactListAdapter manageContactList;
	
	private List<Long> contactsIdList = new ArrayList<Long>();
	
	private List<GuiContact> contactsList = new ArrayList<GuiContact>();
	
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

	public List<Long> getContactsIdList() {
		return contactsIdList;
	}

	public void setContactsIdList(List<Long> contactsIdList) {
		this.contactsIdList = contactsIdList;
	}

	public List<GuiContact> getContactsList() {
		return contactsList;
	}

	public void setContactsList(List<GuiContact> contactsList) {
		this.contactsList = contactsList;
	}
	
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String addContact() {
		//manageContactList.addContact(userBean.getUser(),getUser.getUser(this.contactName));
		contactName = "";
		this.init();
		return "success";
	}

}