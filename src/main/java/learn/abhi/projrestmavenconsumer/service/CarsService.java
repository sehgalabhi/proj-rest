package learn.abhi.projrestmavenconsumer.service;

import learn.abhi.projrestmavenconsumer.model.CarCreationResponse;
import learn.abhi.projrestmavenconsumer.model.CreateCarRequest;

import javax.transaction.Transactional;

@Transactional
public interface CarsService {

    CarCreationResponse addCar(CreateCarRequest createCarRequest);
}
