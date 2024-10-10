package lab.cloud.book.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lab.cloud.book.model.Author;

@Component
public class AuthorDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public Author getAuthor(String authorId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("author-service");

		if (instances.size() == 0)
			return null;
        String serviceUri = String.format("%s/v1/author/%s",instances.get(0).getUri().toString(), authorId);
    
		ResponseEntity<Author> restExchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, Author.class,
				authorId);

        return restExchange.getBody();
    }
}
