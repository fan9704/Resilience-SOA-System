package lab.cloud.author.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lab.cloud.author.model.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, String> {
	public Optional<Author> findById(String authorId);
}
