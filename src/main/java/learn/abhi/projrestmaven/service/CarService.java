package learn.abhi.projrestmaven.service;

import learn.abhi.projrestmaven.model.Car;
import learn.abhi.projrestmaven.model.CarRequest;

import javax.transaction.Transactional;

@Transactional
public interface CarService {

    Car createCar(CarRequest carRequest);
}
