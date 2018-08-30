package messenger.Gui;

public class GuiContact {
	private Long contactId;
	
	private String contactName;

	public GuiContact(Long contactId, String contactName) {
		super();
		this.contactId = contactId;
		this.contactName = contactName;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	

}
