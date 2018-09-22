package messenger.DaoImpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import messenger.Dao.ContactDao;
import messenger.Domain.Contact;
import messenger.Domain.User;

@Service

public class ContactDaoImpl implements ContactDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public <T> void persistObject(T entity) {
		em.persist(entity);
	}
	
	public <T> T find(Class<T> entityClass, Object primaryKey) {
		return em.find(entityClass, primaryKey);
	}

	public <T> void removeObject(T entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}

	public <T> void mergeObject(T entity) {
		em.merge(entity);
	}
	
	public Contact getContact(User user, User contact) {
		TypedQuery<Contact> query = em.createQuery("SELECT contact FROM ClientContact contact WHERE USER_ID = :userId AND CONTACT_ID = :contactId", Contact.class);
		query.setParameter("userId", user.getUserId());
		query.setParameter("contactId", contact.getUserId());
		return query.getSingleResult();
	}
	
	public List<Contact> getContactList(User user) {
		TypedQuery<Contact> query = em.createQuery("SELECT contact FROM ClientContact contact WHERE USER_ID = :userId", Contact.class);
		query.setParameter("userId", user.getUserId());
		List<Contact> contactIdList = query.getResultList();
		return contactIdList;
	}
	
	public void deleteUserFromContacs(User contact) {
		Query query = em.createQuery("DELETE FROM ClientContact WHERE CONTACT_ID = :contactId OR USER_ID = :contactId");
		query.setParameter("contactId", contact.getUserId());
		query.executeUpdate();
	}
}