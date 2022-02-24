package learn.abhi.projrestmavenconsumer.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "carservice.cars")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class ApplicationConfig {

    private String carsBaseUrl;
}
