package lab.cloud.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {

	@Value("${lab.profile}")
	private String exampleProperty;

	@GetMapping(value = "/labprofile")
	public ResponseEntity<String> getExampleProperty() {
		return ResponseEntity.ok(exampleProperty);
	}
	
	@Autowired
	private Environment env;

	@GetMapping(value = "/property/{key}")
	public ResponseEntity<String> getBook(@PathVariable("key") String key) {
		return ResponseEntity.ok(env.getProperty(key));
	}
}
