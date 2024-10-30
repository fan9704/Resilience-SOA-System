package lab.cloud.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lab.cloud.book.model.Author;
import lab.cloud.book.model.Book;
import lab.cloud.book.repository.BookRepository;
import lab.cloud.book.service.client.AuthorDiscoveryClient;
import lab.cloud.book.service.client.AuthorFeignClient;
import lab.cloud.book.service.client.AuthorRestTemplateClient;
import lab.cloud.book.utils.UserContextHolder;

@Service
public class BookService {

	@Autowired
	MessageSource messages;

	@Autowired
	private BookRepository bookRepo;

	@Autowired
	AuthorFeignClient authorFeignClient;

	@Autowired
	AuthorRestTemplateClient authorRestClient;

	@Autowired
	AuthorDiscoveryClient authorDiscoveryClient;

	private static final Logger logger = LoggerFactory.getLogger(BookService.class);

	public Book getBook(String bookId, String authorId, String clientType){
		Book book = bookRepo.findByAuthorIdAndBookId(authorId, bookId);
		if (null == book) {
			throw new IllegalArgumentException(
					String.format(messages.getMessage("book.search.error.message", null, null), bookId, authorId));
		}

		Author author = retrieveAuthorInfo(authorId, clientType);
		if (null != author) {
			book.setAuthorName(author.getName());
			book.setContactName(author.getContactName());
			book.setContactEmail(author.getContactEmail());
			book.setContactPhone(author.getContactPhone());
		}

		return book;
	}

	@CircuitBreaker(name = "authorService")
	private Author retrieveAuthorInfo(String authorId, String clientType) {
		Author author = null;

		switch (clientType) {
		case "feign":
			System.out.println("Calling the feign client");
			author = authorFeignClient.getAuthor(authorId);
			break;
		case "rest":
			System.out.println("Calling the rest client");
			author = authorRestClient.getAuthor(authorId);
			break;
		case "discovery":
			System.out.println("Calling the discovery client");
			author = authorDiscoveryClient.getAuthor(authorId);
			break;
		default:
			author = authorRestClient.getAuthor(authorId);
			break;
		}

		return author;
	}

	public Book createBook(Book book){
		book.setBookId(UUID.randomUUID().toString());
		bookRepo.save(book);
		return book;
	}

	public Book updateBook(Book book){
		bookRepo.save(book);
		return book;
	}

	public String deleteBook(String bookId){
		String responseMessage = null;
		Book book = new Book();
		book.setBookId(bookId);
		bookRepo.delete(book);
		responseMessage = String.format(messages.getMessage("book.delete.message", null, null),bookId);
		return responseMessage;

	}

	@CircuitBreaker(name = "bookService")
//	@CircuitBreaker(name = "bookService", fallbackMethod = "buildFallbackBookList")
//	@RateLimiter(name = "bookService", fallbackMethod = "buildFallbackBookList")
//	@Retry(name = "retryBookService", fallbackMethod = "buildFallbackBookList")
//	@Bulkhead(name = "bulkheadBookService", type= Type.THREADPOOL, fallbackMethod = "buildFallbackBookList")
	public List<Book> getBooksByAuthor(String authorId) throws TimeoutException {
		logger.debug("BookService Correlation id: {}",
				UserContextHolder.getContext().getCorrelationId());
		longRunRandomly();
		return bookRepo.findByAuthorId(authorId);
	}

	@SuppressWarnings("unused")
	private List<Book> buildFallbackBookList(String authorId, Throwable t){
		List<Book> fallbackList = new ArrayList<>();
		Book book = new Book();
		book.setBookId("0000000-00-00000");
		book.setAuthorId(authorId);
		book.setProductName("No book information is currently available");
		fallbackList.add(book);
		return fallbackList;
	}

	private static void longRunRandomly() throws TimeoutException{
		int max = 3;
		int min = 1;
		int randomNum = new Random().nextInt((max - min + 1) + min);
		if (randomNum==3) sleep();
	}
	private static void sleep() throws TimeoutException{
		try {
			System.out.println("Sleeping....zzz");
			Thread.sleep(5000);
			throw new java.util.concurrent.TimeoutException();
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
	}
	
}
