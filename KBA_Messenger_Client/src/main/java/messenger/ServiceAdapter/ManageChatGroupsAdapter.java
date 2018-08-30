package messenger.ServiceAdapter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import messenger.Domain.Chat;

@Service
public class ManageChatGroupsAdapter {

	public boolean addConversation(Chat chat) {
		final String uri = "http://localhost:8081/messenger/manageChatGroups/addConversation";
		
	    RestTemplate restTemplate = new RestTemplate();
	    boolean result = restTemplate.postForObject(uri, chat, boolean.class);
	    return result;
	}
	

	public boolean deleteConveration(Chat chat) {
		final String uri = "http://localhost:8081/messenger/manageChatGroups/deleteConveration";
		
	    RestTemplate restTemplate = new RestTemplate();
	    boolean result = restTemplate.postForObject(uri, chat, boolean.class);
	    return result;
	}
	

	public boolean updateConversation(Chat chat) {
		final String uri = "http://localhost:8081/messenger/manageChatGroups/updateConversation";
		
	    RestTemplate restTemplate = new RestTemplate();
	    boolean result = restTemplate.postForObject(uri, chat, boolean.class);
	    return result;
	}
		

	public boolean getConversation(Chat chat) {
		final String uri = "http://localhost:8081/messenger/manageChatGroups/getConversation";
		
	    RestTemplate restTemplate = new RestTemplate();
	    boolean result = restTemplate.postForObject(uri, chat, boolean.class);
	    return result;
	}	

}
