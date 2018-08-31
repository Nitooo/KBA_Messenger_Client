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
public class ChatBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserBean userBean;

	@Autowired
	private ManageChatGroupsAdapter manageChatGroups;
	
	private List<Chat> chatList;
	
	private Chat newChat;
    
    @PostConstruct
    private void init() {
    	setChatList(userBean.getUser().getChats());
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


	public List<Chat> getChatList() {
		return chatList;
	}

	public Chat getChat() {
		return newChat;
	}

	public void setChat(Chat chat) {
		this.newChat = chat;
	}

	public void setChatList(List<Chat> chatList) {
		this.chatList = chatList;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	public List<Long> getAllConversations(Long userId) {
		//return manageChatGroups.getAllConversations(userId);
		return null;
	}

    public String showChat() {
        return "success";
    }
    
    public String addGroupChat(String chatName) {
    	newChat = new Chat();
    	newChat.setName(chatName);
    	newChat.setAdmin(userBean.getUser());
    	newChat.setGroupChat(true);
    	manageChatGroups.addConversation(newChat);
    	return "successAddGroupChat";
    }
    
    public String addChat(String chatName) {
    	newChat = new Chat();
    	newChat.setName(chatName);
    	newChat.setAdmin(userBean.getUser());
    	newChat.setGroupChat(false);
    	manageChatGroups.addConversation(newChat);
    	return "successAddChat";
    }
}
