package messenger.ServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import messenger.Dao.ContactDao;
import messenger.Domain.Contact;
import messenger.Domain.User;
import messenger.Service.ManageContactList;
import messenger.ServiceAdapter.UserManagementAdapter;

@Service
public class ManageContactListImpl implements ManageContactList, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContactDao contactDbService;

	@Autowired
	private UserManagementAdapter userManagement;
	
	@Override
	@Transactional
	public boolean addContact(User user, User contact) {
		Contact clientContact;
		
		try{
			clientContact = contactDbService.getContact(user, contact);
			return false;
		} catch (Exception e) {
			clientContact = new Contact();
			clientContact.setContactId(contact.getUserId());
			clientContact.setUserId(user.getUserId());			
		}	
		


		try{
			contactDbService.persistObject(clientContact);
			return true;
		} catch (Exception e) {
			return false;
		}	
	}

	@Override
	@Transactional
	public boolean deleteContact(User user, User contact) {
		Contact clientContact;
		
		try{
			clientContact = contactDbService.getContact(user, contact);
		} catch (Exception e) {
			return false;
		}	
		
		
		try{
			contactDbService.removeObject(clientContact);
			return true;
		} catch (Exception e) {
			return false;
		}	
	}
	
	@Override
	@Transactional
	public Set<User> getContactList(User user) {
		Set<User> contactList = new HashSet<User>();
		
		List<Contact> contactIdList = contactDbService.getContactList(user);
		
		for(Contact clientContact : contactIdList) {
			contactList.add(userManagement.getUserById(clientContact.getContactId()));
		}
		return contactList;
	}

}
