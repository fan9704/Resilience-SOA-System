package lab.cloud.author.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lab.cloud.author.model.Author;
import lab.cloud.author.service.AuthorService;

@RestController
@RequestMapping(value = "v1/author")
public class AuthorController {
	@Autowired
	private AuthorService service;


	@RequestMapping(value = "/{authorId}", method = RequestMethod.GET)
	public ResponseEntity<Author> getAuthor(@PathVariable("authorId") String authorId) {
		return ResponseEntity.ok(service.findById(authorId));
	}

	@RequestMapping(value = "/{authorId}", method = RequestMethod.PUT)
	public void updateAuthor(@PathVariable("authorId") String id, @RequestBody Author author) {
		service.update(author);
	}

	@PostMapping
	public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
		return ResponseEntity.ok(service.create(author));
	}

	@RequestMapping(value = "/{authorId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAuthor(@PathVariable("id") String id, @RequestBody Author author) {
		service.delete(author);
	}

}
