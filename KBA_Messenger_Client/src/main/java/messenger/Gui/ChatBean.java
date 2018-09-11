package messenger.Gui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import messenger.Domain.Chat;
import messenger.Domain.User;
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
    	//CHat zum Testen der Gui
    	/*Chat newGroupchat = new Chat();
    	newGroupchat.setAdmin(userBean.getUser());
    	newGroupchat.setName("TestGuiChat");
    	newGroupchat.setGroupChat(true);
    	List<User> users = new ArrayList<User>();
    	users.add(userBean.getUser());
    	users.add(userBean.getUser());
    	newGroupchat.setUsers(users);
    	chatList.add(newGroupchat);
    	Chat newSingleChat = new Chat();
    	newSingleChat.setGroupChat(false);
    	chatList.add(newSingleChat);*/
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
		//manageChatGroups.updateConversation(this.chat);
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
    public String editChat(Chat newChat) {
    	this.setChat(newChat);
        return "editChat";
    }
    

}
