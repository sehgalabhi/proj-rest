package learn.abhi.projrestmavenconsumer;

import learn.abhi.projrestmavenconsumer.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationConfig.class})
public class ProjRestMavenconsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjRestMavenconsumerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
