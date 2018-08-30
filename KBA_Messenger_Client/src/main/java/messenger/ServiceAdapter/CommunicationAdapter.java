package messenger.ServiceAdapter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import messenger.Domain.Chat;
import messenger.Domain.Message;

@Service
public class CommunicationAdapter {

	public boolean sendMessage(Message message) {
		final String uri = "http://localhost:8081/messenger/communication/sendMessage";
		
	    RestTemplate restTemplate = new RestTemplate();
	    boolean result = restTemplate.postForObject(uri, message, boolean.class);
	    return result;
	}

	
	public Chat getChat(Chat chat) {
		final String uri = "http://localhost:8081/messenger/communication/recieveMessage";
		
		RestTemplate restTemplate = new RestTemplate();
	    Chat result = restTemplate.postForObject(uri, chat, Chat.class);
	    return result;
	}

}
