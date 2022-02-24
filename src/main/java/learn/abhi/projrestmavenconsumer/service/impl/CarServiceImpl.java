package learn.abhi.projrestmavenconsumer.service.impl;

import learn.abhi.projrestmavenconsumer.config.ApplicationConfig;
import learn.abhi.projrestmavenconsumer.model.CreateCarRequest;
import learn.abhi.projrestmavenconsumer.service.CarsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarsService {

    private RestTemplate restTemplate;

    private ApplicationConfig applicationConfig;

    public CreateCarRequest addCar(CreateCarRequest createCarRequest){

        final String carsUrl = UriComponentsBuilder.fromHttpUrl(applicationConfig.getCarsBaseUrl())
                .path("cars")
                .toUriString();


        CreateCarRequest response = restTemplate.postForObject(carsUrl, createCarRequest, CreateCarRequest.class);
        return response;

    }
}
