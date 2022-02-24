package learn.abhi.projrestmaven.controller;

import learn.abhi.projrestmaven.model.Car;
import learn.abhi.projrestmaven.model.CarRequest;
import learn.abhi.projrestmaven.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("cars")
public class CarController {

    private final CarService carService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Car> createCars(@RequestBody CarRequest carRequest){
        final Car car1 = carService.createCar(carRequest);

        return new ResponseEntity<>(car1, HttpStatus.CREATED);

    }

}
