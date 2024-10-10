package lab.cloud.book.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lab.cloud.book.model.Author;

@FeignClient("author-service")
public interface AuthorFeignClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/v1/author/{authorId}", consumes = "application/json")
    Author getAuthor(@PathVariable("authorId") String authorId);
}
