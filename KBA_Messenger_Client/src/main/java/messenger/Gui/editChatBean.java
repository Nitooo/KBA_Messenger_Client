package messenger.Gui;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import messenger.Domain.User;
import messenger.ServiceAdapter.CommunicationAdapter;
import messenger.ServiceAdapter.GetUserAdapter;
import messenger.ServiceAdapter.ManageChatGroupsAdapter;


@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class editChatBean implements Serializable{
	
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
	
	@Autowired
	private GetUserAdapter getUser;
	
	private String admin;
	
	private String usernameInput;
	
	private String chatname;
	
    @PostConstruct
    private void init() {  
    	
    }

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
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
    
	public ChatBean getChatBean() {
		return chatBean;
	}

	public void setChatBean(ChatBean chatBean) {
		this.chatBean = chatBean;
	}

	public GetUserAdapter getGetUser() {
		return getUser;
	}

	public void setGetUser(GetUserAdapter getUser) {
		this.getUser = getUser;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}


	public String getUsernameInput() {
		return usernameInput;
	}

	public void setUsernameInput(String usernameInput) {
		this.usernameInput = usernameInput;
	}

	public String getChatname() {
		return chatname;
	}

	public void setChatname(String chatname) {
		this.chatname = chatname;
	}

	public void deleteUserFromChat(User user) {
		List<User> userList = chatBean.getChat().getUsers();
		userList.remove(user);
		chatBean.getChat().setUsers(userList);
		chatBean.updateChat();
		
	}
	
	public void addUserToChat() {
		User user = getUser.getUser(this.usernameInput);
		
		if(user!=null) {
			List<User> userList = chatBean.getChat().getUsers();
			userList.add(user);
			chatBean.getChat().setUsers(userList);
			chatBean.updateChat();
		} else {
			warn("User " + this.usernameInput + " wurde nicht gefunden!");
		}
	}
	
	public void changeChatName() {
		chatBean.getChat().setName(this.chatname);
		chatBean.updateChat();
	}
	
	public void changeChatAdmin() {
		User user = getUser.getUser(this.admin);
		if(user!=null) {
			chatBean.getChat().setAdmin(user);
			chatBean.updateChat();
		}
		
	}
	
	public void warn(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warnung!", message));
    }
  	
  	public void error(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler!", message));
    }
	
	
    
    }
    
    