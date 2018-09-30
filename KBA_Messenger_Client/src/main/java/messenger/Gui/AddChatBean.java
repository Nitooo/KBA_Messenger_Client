package messenger.Gui;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import messenger.ServiceAdapter.ManageChatGroupsAdapter;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AddChatBean implements Serializable {

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

	private ErrorMessagesGui errorMessages = new ErrorMessagesGui();

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

	/**
	 * adds Groupconversation
	 * 
	 * @return is used for navigation(faces-config.xml)
	 */
	public String addGroupConversation() {
		try {
			manageChatGroups.addGroupConversation(userBean.getUser(), this.chatname);
			chatBean.resetChatBean();
			return "successAddGroupChat";
		} catch (Exception e) {
			errorMessages.error("Es ist ein kritischer Fehler aufgetreten!");
			return "error";
		}

	}

	/**
	 * adds Groupconversation
	 * 
	 * @return is used for navigation(faces-config.xml)
	 */
	public String addConversation() {
		try {
			manageChatGroups.addConversation(userBean.getUser());
			chatBean.resetChatBean();
			return "successAddChat";
		} catch (Exception e) {
			errorMessages.error("Es ist ein kritischer Fehler aufgetreten!");
			return "error";
		}
	}
}
