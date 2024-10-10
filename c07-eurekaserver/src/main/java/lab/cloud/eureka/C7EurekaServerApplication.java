package lab.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class C7EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(C7EurekaServerApplication.class, args);
	}

}
