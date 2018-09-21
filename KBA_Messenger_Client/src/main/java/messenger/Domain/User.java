package messenger.Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.JoinColumn;

@Entity(name = "User")
public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue
    @Id
    @Column(name = "USER_ID")
    private Long userId;
    
    @Column(name = "USERNAME")
    private String username;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value={ "contacts", "contactOf" }, allowSetters = true)
    @JoinTable(name = "CONTACTS",
    	joinColumns = @JoinColumn(name = "USER_ID"),
    	inverseJoinColumns = @JoinColumn(name = "CONTACT_ID")
    )
    private Set<User> contacts = new HashSet<User>();
    
	@ManyToMany(mappedBy = "contacts", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value={ "contacts", "contactOf" }, allowSetters = true)
    private Set<User> contactOf = new HashSet<User>();
    
	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "users", allowSetters = true)
	private List<Chat> chats = new ArrayList<Chat>();
	
    public Set<User> getContacts() {
		return contacts;
	}
	
	public void setContacts(Set<User> contacts) {
	this.contacts = contacts;
}


	public List<Chat> getChats() {
		return chats;
	}


	public void setChats(List<Chat> chats) {
		this.chats = chats;
	}




public Set<User> getContactOf() {
		return contactOf;
	}


	public void setContactOf(Set<User> contactOf) {
		this.contactOf = contactOf;
	}



	
	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

}
