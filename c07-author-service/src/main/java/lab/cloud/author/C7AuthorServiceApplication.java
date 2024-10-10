package lab.cloud.author;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class C7AuthorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(C7AuthorServiceApplication.class, args);
	}

}
