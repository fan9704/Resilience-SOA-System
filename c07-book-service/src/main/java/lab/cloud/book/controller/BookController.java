package lab.cloud.book.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lab.cloud.book.model.Book;
import lab.cloud.book.service.BookService;
import lab.cloud.book.utils.UserContextHolder;

@RestController
@RequestMapping(value="v1/author/{authorId}/book")
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	@RequestMapping(value="/{bookId}",method = RequestMethod.GET)
	public ResponseEntity<Book> getBook( @PathVariable("authorId") String authorId,
			@PathVariable("bookId") String bookId) {

		Book book = bookService.getBook(bookId, authorId, "");
		book.add( 
				linkTo(methodOn(BookController.class).getBook(authorId, book.getBookId())).withSelfRel(),
				linkTo(methodOn(BookController.class).createBook(book)).withRel("createBook"),
				linkTo(methodOn(BookController.class).updateBook(book)).withRel("updateBook"),
				linkTo(methodOn(BookController.class).deleteBook(book.getBookId())).withRel("deleteBook")
				);

		return ResponseEntity.ok(book);
	}

	@RequestMapping(value="/{bookId}/{clientType}",method = RequestMethod.GET)
	public Book getBooksWithClient( @PathVariable("authorId") String authorId,
			@PathVariable("bookId") String bookId,
			@PathVariable("clientType") String clientType) {

		return bookService.getBook(bookId, authorId, clientType);
	}

	@PutMapping
	public ResponseEntity<Book> updateBook(@RequestBody Book request) {
		return ResponseEntity.ok(bookService.updateBook(request));
	}

	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book request) {
		return ResponseEntity.ok(bookService.createBook(request));
	}

	@DeleteMapping(value="/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable("bookId") String bookId) {
		return ResponseEntity.ok(bookService.deleteBook(bookId));
	}

	@RequestMapping(value="/",method = RequestMethod.GET)
	public List<Book> getBooks( @PathVariable("authorId") String authorId) throws TimeoutException {
		logger.debug("BookController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
		return bookService.getBooksByAuthor(authorId);
	}

}
