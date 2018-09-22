package messenger.Dao;

import java.util.List;

import messenger.Domain.Contact;
import messenger.Domain.User;

public interface ContactDao {

	/**
	 * Generische Methode, um die übergebene Entität als Eintrag in der Datenbank zu
	 * erzeugen.
	 * 
	 * @param entity
	 *            generische Entität, welche in die Datenbank persistiert werden
	 *            soll
	 */
	public <T> void persistObject(T entity);

	/**
	 * Generische Methode, um die übergebene Entität als Eintrag in der Datenbank zu
	 * finden.
	 * 
	 * @param entity
	 *            generische Entität, welche in die Datenbank gefunden werden
	 *            soll
	 */
	public <T> T find(Class<T> entityClass, Object primaryKey);
	
	/**
	 * Generische Methode, um den Eintrag der übergebenen Entität in der Datenbank
	 * zu aktualisieren.
	 * 
	 * @param entity
	 *            generische Entität, dessen Eintrag in der Datenbank aktualisiert
	 *            werden soll
	 */
	public <T> void mergeObject(T entity);

	/**
	 * Generische Methode, um den Eintrag der übergebenen Entität aus der Datenbank
	 * zu entfernen.
	 * 
	 * @param entity
	 *            generische Entität, dessen Eintrag aus der Datenbank entfernt
	 *            werden soll
	 */
	public <T> void removeObject(T entity);

	/**
	 * returns the Contact between two users
	 * @param user owner of the contact
	 *        contact contact of the owner
	 * @return Contact object of two users
	 */
	
	public Contact getContact(User user, User contact);
	
	/**
	 * returns a list with all the contact of a user
	 * @param user owner of contact list
	 * @return List of contacts belonging to the contacts of the user
	 */
	public List<Contact> getContactList(User user);
	
	/**
	 * deletes all Contact objects where the user is involved
	 * @param user in the contact list
	 */
	public void deleteUserFromContacs(User contact);
	
}
