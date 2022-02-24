package learn.abhi.projrestmaven.service;

import learn.abhi.projrestmaven.model.CarCreationRequest;
import learn.abhi.projrestmaven.model.CarCreationResponse;

import javax.transaction.Transactional;

@Transactional
public interface CarService {

    CarCreationResponse createCar(CarCreationRequest carCreationRequest);
}
