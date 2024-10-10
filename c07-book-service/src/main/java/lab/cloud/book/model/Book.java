package lab.cloud.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="books")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book extends RepresentationModel<Book> {

	@Id
	@Column(name = "book_id", nullable = false)
	private String bookId;
	private String description;
	@Column(name = "author_id", nullable = false)
	private String authorId;
	@Column(name = "product_name", nullable = false)
	private String productName;
	@Column(name = "book_type", nullable = false)
	private String bookType;
	@Column(name="comment")
	private String comment;
	@Transient
	private String authorName;
	@Transient
	private String contactName;
	@Transient
	private String contactPhone;
	@Transient
	private String contactEmail;

}