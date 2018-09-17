package messenger.Gui;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import messenger.Domain.Chat;
import messenger.ServiceAdapter.CommunicationAdapter;
import messenger.ServiceAdapter.ManageChatGroupsAdapter;


@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ChatBean implements Serializable{
	
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
	
	private List<Chat> chatList;
	
	private Chat chat;
    
    @PostConstruct
    private void init() {
    	chatList = userBean.getUser().getChats();
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
		this.chat = newChat;
		refreshChat();
		return "openChat";
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
		this.chat = communication.getChat(this.chat);
	}
	
	
	/**
	 * sends updated chat to the server
	 */
	public void updateChat() {
		manageChatGroups.updateConversation(this.chat);
		refreshChat();
	}
	
	public String resetChatBean(){
		userBean.refreshUser();
    	FacesContext
		.getCurrentInstance()
		.getExternalContext()
		.invalidateSession();
    	return "chatList?faces-redirect=true";
    }
	
	
	/**
	 * sets chat before the chatinfo-page is opened
	 * @param newChat
	 * @return
	 */
    public String showChatInfo(Chat newChat) {
    	this.setChat(newChat);
        return "showInfo";
    }
    
    /**
     * sets Chat before the ChatEdit-Page is opened
     * @param newChat
     * @return
     */
    public String editGroupConversation(Chat newChat) {
    	this.setChat(newChat);
    	return "editGroupConversation";
    }
    
    /**
     * sets Chat before the ChatEdit-Page is opened
     * @param newChat
     * @return
     */
    public String editConversation(Chat newChat) {
    	this.setChat(newChat);
    	return "editConversation";
    }
    
    public String deleteConversation(Chat newchat) {
		manageChatGroups.deleteConveration(newchat);
    	return "conversationDeleted";
    }

}
