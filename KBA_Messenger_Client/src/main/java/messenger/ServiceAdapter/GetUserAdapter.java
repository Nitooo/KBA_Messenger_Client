package messenger.ServiceAdapter;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import messenger.Domain.User;

@Service
public class GetUserAdapter {

	public User getUser(String username) {
		final String uri = "http://localhost:8081/messenger/getUser/getUser?username=" + username;

		RestTemplate restTemplate = new RestTemplate();
		User result = restTemplate.getForObject(uri, User.class);
		return result;
	}
}
