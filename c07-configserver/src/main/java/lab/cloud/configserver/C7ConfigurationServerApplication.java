package lab.cloud.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class C7ConfigurationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(C7ConfigurationServerApplication.class, args);
	}

}
