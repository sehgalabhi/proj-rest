package learn.abhi.projrestmavenconsumer.service.impl;

import learn.abhi.projrestmavenconsumer.model.CreateCarRequest;
import learn.abhi.projrestmavenconsumer.service.CarsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CarsServiceImpl implements CarsService {

    private RestTemplate restTemplate;

    public CreateCarRequest addCar(CreateCarRequest createCarRequest){
        CreateCarRequest response = restTemplate.postForObject("http://localhost:8080/cars", createCarRequest, CreateCarRequest.class);
        return response;

    }
}
