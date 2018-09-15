package messenger.ServiceAdapter;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import messenger.Domain.User;

@Service
public class UserManagementAdapter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public int addUser(String username, String passwort) {
		final String uri = "http://localhost:8081/messenger/userManagement/addUser?username=" + username + "&passwort=" + passwort;
	     
	    RestTemplate restTemplate = new RestTemplate();
	    int result = restTemplate.getForObject(uri, int.class);
	    return result;
	}
	
	public boolean deleteUser(User user) {
		final String uri = "http://localhost:8081/messenger/userManagement/deleteUser";
		
	    RestTemplate restTemplate = new RestTemplate();
	    boolean result = restTemplate.postForObject(uri, user, boolean.class);
	    return result;
	}
	

	public boolean updateUser(User user) {
		final String uri = "http://localhost:8081/messenger/userManagement/updateUser";
		
	    RestTemplate restTemplate = new RestTemplate();
	    boolean result = restTemplate.postForObject(uri, user, boolean.class);
	    return result;
	}
	
	public User getUser(User user) {
		final String uri = "http://localhost:8081/messenger/userManagement/getUser";
		
	    RestTemplate restTemplate = new RestTemplate();
	    User result = restTemplate.postForObject(uri, user, User.class);
	    return result;
	}
	
	public User getUserById(Long userId) {
		final String uri = "http://localhost:8081/messenger/userManagement/getUserById";
		
	    RestTemplate restTemplate = new RestTemplate();
	    User result = restTemplate.postForObject(uri, userId, User.class);
	    return result;
	}
	

	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public User[] getAllUsers() {
		final String uri = "http://localhost:8081/messenger/userManagement/getAllUsers";
		
	    RestTemplate restTemplate = new RestTemplate();
	    User[] result = restTemplate.getForObject(uri, User[].class);
	    return result;
	}

}
