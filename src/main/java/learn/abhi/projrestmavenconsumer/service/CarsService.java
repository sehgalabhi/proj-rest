package learn.abhi.projrestmavenconsumer.service;

import learn.abhi.projrestmavenconsumer.model.CreateCarRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
public interface CarsService {

    CreateCarRequest addCar(CreateCarRequest createCarRequest);
}
