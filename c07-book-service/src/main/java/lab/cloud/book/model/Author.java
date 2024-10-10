package lab.cloud.book.model;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Author extends RepresentationModel<Author> {

	String id;
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;
    
}
