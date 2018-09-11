package messenger.Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Chat implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue
    @Id
    @Column(name = "CHAT_ID")
    private Long chatId;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_CHAT",
    	joinColumns = @JoinColumn(name = "CHAT_ID"),
    	inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    @JsonIgnoreProperties(value = "chats", allowSetters = true)
	private List<User> users = new ArrayList<User>();
    
    @OneToMany(mappedBy = "chat", cascade = CascadeType.REMOVE)
    //@JsonIgnoreProperties(value = "sender", allowSetters = true)
	private List<Message> messages = new ArrayList<Message>();
	
	@Column(name = "NAME")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "ADMIN_ID")
    @JsonIgnoreProperties(value = "chats", allowSetters = true)
	private User admin;
	
	@Column(name = "ISGROUPCHAT")
	private boolean isGroupChat;
	
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}


	public boolean isGroupChat() {
		return isGroupChat;
	}

	public void setGroupChat(boolean isGroupChat) {
		this.isGroupChat = isGroupChat;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}
	
	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

}
