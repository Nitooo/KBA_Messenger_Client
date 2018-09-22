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

	 */
	public Contact getContact(User user, User contact);
	
	public List<Contact> getContactList(User user);
	
}
