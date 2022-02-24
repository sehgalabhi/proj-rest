package learn.abhi.projrestmaven.controller;

import learn.abhi.projrestmaven.model.CarCreationRequest;
import learn.abhi.projrestmaven.model.CarCreationResponse;
import learn.abhi.projrestmaven.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("cars")
public class CarController {

    private final CarService carService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseBody
    //todo add validation
    public ResponseEntity<CarCreationResponse> createCars(@Validated @RequestBody CarCreationRequest carCreationRequest){
        final CarCreationResponse response = carService.createCar(carCreationRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
