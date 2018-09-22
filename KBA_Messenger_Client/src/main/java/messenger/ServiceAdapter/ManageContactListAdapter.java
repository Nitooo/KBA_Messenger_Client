package messenger.ServiceAdapter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import messenger.Domain.User;

@Service
public class ManageContactListAdapter {

	public boolean addContact(User user, User contact) {
		final String uri = "http://localhost:8081/messenger/manageContactList/addContact";
		
		LinkedMultiValueMap<String,User> parameters = new LinkedMultiValueMap<String,User>();
		parameters.add("user", user);
		parameters.add("contact", contact);

		LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/json");
		
		HttpEntity<LinkedMultiValueMap<String,User>> entity = new HttpEntity<LinkedMultiValueMap<String, User>>(parameters, headers);		

	    RestTemplate restTemplate = new RestTemplate();
	    boolean result = restTemplate.postForObject(uri, entity, boolean.class);
	    
	    return result;
	}
	
	public boolean deleteContact(User user, User contact) {
		final String uri = "http://localhost:8081/messenger/manageContactList/deleteContact";
		
		MultiValueMap<String,User> parameters = new LinkedMultiValueMap<String,User>();
		parameters.add("user", user);
		parameters.add("contact", contact);

		LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/json");
		
		HttpEntity<MultiValueMap<String,User>> entity = new HttpEntity<MultiValueMap<String, User>>(parameters, headers);		
		
	    RestTemplate restTemplate = new RestTemplate();
	    boolean result = restTemplate.postForObject(uri, entity, boolean.class);
	    
	    return result;
	}
}
