package messenger.ServiceAdapter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatValidationAdapter {
	
	

	public boolean checkIfChatExists(Long chatId)
	{
		final String uri = "http://localhost:8081/messenger/chatValidation/checkIfChatExists.json";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    boolean result = restTemplate.getForObject(uri, boolean.class);
	    return result;
	}		
}
