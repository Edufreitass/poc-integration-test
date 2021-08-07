package codeplays.trainee.pocintegrationtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PocintegrationtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocintegrationtestApplication.class, args);
	}

}
