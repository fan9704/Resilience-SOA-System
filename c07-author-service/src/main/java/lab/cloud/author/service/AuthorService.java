package lab.cloud.author.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.cloud.author.model.Author;
import lab.cloud.author.repository.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository repository;

	public Author findById(String authorId) {
		Optional<Author> opt = repository.findById(authorId);
		return (opt.isPresent()) ? opt.get() : null;
	}

	public Author create(Author author) {
		author.setId(UUID.randomUUID().toString());
		author = repository.save(author);
		return author;

	}

	public void update(Author author) {
		repository.save(author);
	}

	public void delete(Author author) {
		repository.deleteById(author.getId());
	}
}