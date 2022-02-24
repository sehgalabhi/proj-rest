package learn.abhi.projrestmaven.service.impl;

import learn.abhi.projrestmaven.model.CarCreationRequest;
import learn.abhi.projrestmaven.model.CarCreationResponse;
import learn.abhi.projrestmaven.service.CarService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;

@Service
public class CarServiceImpl implements CarService {


    @Override
    public CarCreationResponse createCar(CarCreationRequest carCreationRequest) {
        final String responseUuid = randomUUID().toString();
        final LocalDateTime creationTime = now();

        return createResponse(carCreationRequest, responseUuid, creationTime);

    }

    private CarCreationResponse createResponse(CarCreationRequest carCreationRequest, String responseUuid, LocalDateTime creationTime) {
        //todo use builder
        return  new CarCreationResponse(carCreationRequest.getRequestUuid(), responseUuid, carCreationRequest.getName(),
                carCreationRequest.getCarBrand(), carCreationRequest.getSeats(), creationTime);


    }
}
