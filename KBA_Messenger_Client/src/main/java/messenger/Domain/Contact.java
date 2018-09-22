package messenger.Domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "ClientContact")
public class Contact implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@GeneratedValue
//    @Id
//    @Column(name = "ID")
//    private Long clientContactId;
	
	@Id
    @Column(name = "USER_ID")
    private Long userId;
    
	@Id
    @Column(name = "CONTACT_ID")
    private Long contactId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	

}
