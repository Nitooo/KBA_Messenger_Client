package messenger.Gui;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import messenger.Domain.Chat;
import messenger.ServiceAdapter.CommunicationAdapter;
import messenger.ServiceAdapter.ManageChatGroupsAdapter;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ChatBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserBean userBean;

	@Autowired
	private ManageChatGroupsAdapter manageChatGroups;

	@Autowired
	private CommunicationAdapter communication;

	ErrorMessagesGui errorMessages = new ErrorMessagesGui();

	private List<Chat> chatList;

	private Chat chat;

	@PostConstruct
	private void init() {
		try {
			chatList = userBean.getUser().getChats();
		} catch (Exception e) {
			errorMessages.error("Es ist ein kritischer Fehler aufgetreten!");
		}
	}

	public ManageChatGroupsAdapter getManageChatGroups() {
		return manageChatGroups;
	}

	public void setManageChatGroups(ManageChatGroupsAdapter manageChatGroups) {
		this.manageChatGroups = manageChatGroups;
	}

	public CommunicationAdapter getCommunication() {
		return communication;
	}

	public void setCommunication(CommunicationAdapter communication) {
		this.communication = communication;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public List<Chat> getChatList() {
		return chatList;
	}

	public Chat getChat() {
		return chat;
	}

	public String setChat(Chat newChat) {
		try {
			this.chat = newChat;
			refreshChat();
			return "openChat";
		} catch (ResourceAccessException e) {
			errorMessages.error("Verbindung mit Server ist nicht möglich!");
			return "error";
		} catch (Exception e) {
			errorMessages.error("Es ist ein kritischer Fehler aufgetreten!");
			return "error";
		}

	}

	public void setChatList(List<Chat> chatList) {
		this.chatList = chatList;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	/**
	 * gets a new version for the selected chat from the server
	 */
	public void refreshChat() {
		try {
			this.chat = communication.getChat(this.chat);
		} catch (Exception e) {
			errorMessages.error("Es ist ein kritischer Fehler aufgetreten!");
		}
	}

	/**
	 * sends updated chat to the server
	 */
	public void updateChat() {
		try {
			manageChatGroups.updateConversation(this.chat);
			refreshChat();
		} catch (Exception e) {
			errorMessages.error("Es ist ein kritischer Fehler aufgetreten!");
		}
	}

	public String resetChatBean() {

		try {
			userBean.refreshUser();
			this.init();
			return "reset";
		} catch (ResourceAccessException e) {
			errorMessages.error("Verbindung mit Server ist nicht möglich!");
			return "error";
		} catch (Exception e) {
			errorMessages.error("Es ist ein kritischer Fehler aufgetreten!");
			return "error";
		}

	}

	/**
	 * sets chat before the chatinfo-page is opened
	 * 
	 * @param newChat
	 * @return
	 */
	public String showChatInfo(Chat newChat) {
		this.setChat(newChat);
		return "showInfo";
	}

	/**
	 * sets Chat before the ChatEdit-Page is opened
	 * 
	 * @param newChat
	 * @return
	 */
	public String editGroupConversation(Chat newChat) {
		this.setChat(newChat);
		return "editGroupConversation";
	}

	/**
	 * sets Chat before the ChatEdit-Page is opened
	 * 
	 * @param newChat
	 * @return
	 */
	public String editConversation(Chat newChat) {
		this.setChat(newChat);
		return "editConversation";
	}

	public String deleteConversation(Chat newchat) {
		try {
			manageChatGroups.deleteConveration(newchat);
			this.resetChatBean();
			return "conversationDeleted";
		} catch (Exception e) {
			errorMessages.error("Es ist ein kritischer Fehler aufgetreten!");
			return "error";
		}
	}

}
