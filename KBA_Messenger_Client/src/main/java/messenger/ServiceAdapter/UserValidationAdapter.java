package messenger.ServiceAdapter;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Service
public class UserValidationAdapter {

	public boolean checkIfUserExists(@RequestParam(value = "userId") Long username) {
		final String uri = "http://localhost:8081/messenger/userValidation/checkIfUserExists.json";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    boolean result = restTemplate.getForObject(uri, boolean.class);
	    return result;
	}
	
}
