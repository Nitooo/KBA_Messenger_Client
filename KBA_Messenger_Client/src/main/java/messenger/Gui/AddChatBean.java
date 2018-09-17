package messenger.Gui;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import messenger.Domain.Chat;
import messenger.ServiceAdapter.CommunicationAdapter;
import messenger.ServiceAdapter.ManageChatGroupsAdapter;


@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AddChatBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserBean userBean;
	
	@Autowired
	private ChatBean chatBean;

	@Autowired
	private ManageChatGroupsAdapter manageChatGroups;
	
	String chatname;
    
    public String getChatname() {
		return chatname;
	}

	public void setChatname(String chatname) {
		this.chatname = chatname;
	}

	public ManageChatGroupsAdapter getManageChatGroups() {
		return manageChatGroups;
	}

	public void setManageChatGroups(ManageChatGroupsAdapter manageChatGroups) {
		this.manageChatGroups = manageChatGroups;
	}

	public UserBean getUserBean() {
		return userBean;
	}
    
    public String addGroupConversation() {
    	manageChatGroups.addGroupConversation(userBean.getUser(),this.chatname);
    	chatBean.resetChatBean();
    	return "successAddGroupChat";
    }
    
    public String addConversation() {
    	manageChatGroups.addConversation(userBean.getUser());
    	chatBean.resetChatBean();
    	return "successAddChat";
    }
}
