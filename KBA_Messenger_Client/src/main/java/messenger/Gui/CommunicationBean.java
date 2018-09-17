package messenger.Gui;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import messenger.Domain.Message;
import messenger.ServiceAdapter.CommunicationAdapter;
import messenger.ServiceAdapter.ManageChatGroupsAdapter;


@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CommunicationBean implements Serializable{
	
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
	
	@Autowired
	private CommunicationAdapter communication;
	
	private List<Message> messageList = new ArrayList<Message>();
	
	private String messageText;
	
	private Message message;
	
    
    @PostConstruct
    private void init() {
    	messageList = chatBean.getChat().getMessages();
    }




	public UserBean getUserBean() {
		return userBean;
	}




	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}




	public ChatBean getChatBean() {
		return chatBean;
	}




	public void setChatBean(ChatBean chatBean) {
		this.chatBean = chatBean;
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




	public List<Message> getMessageList() {
		return messageList;
	}




	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}




	public String getMessageText() {
		return messageText;
	}




	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}




	public Message getMessage() {
		return message;
	}




	public void setMessage(Message message) {
		this.message = message;
	}
	
	
	public void sendMessage() {
		message = new Message();
		message.setSender(userBean.getUser());
		message.setText(messageText);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		message.setTimestamp(timestamp);
		message.setChat(chatBean.getChat());
		communication.sendMessage(message);
		
		chatBean.refreshChat();
		this.init();
		
	}
    
}
