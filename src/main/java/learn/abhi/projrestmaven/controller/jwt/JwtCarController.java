package learn.abhi.projrestmaven.controller.jwt;

import learn.abhi.projrestmaven.model.Car;
import learn.abhi.projrestmaven.model.CarCreationRequest;
import learn.abhi.projrestmaven.model.CarCreationResponse;
import learn.abhi.projrestmaven.service.CarService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/jwt/cars")
@Slf4j
public class JwtCarController {

    private final CarService carService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseBody
    //todo add validation
    public ResponseEntity<CarCreationResponse> createCars(@Validated @RequestBody CarCreationRequest carCreationRequest){
        log.info("Called post cars for JwtController");

        final CarCreationResponse response = carService.createCar(carCreationRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping(produces = "application/json")
    @ResponseBody
    //todo complete
    public ResponseEntity<List<Car>> getCars(){
        log.info("Called get cars for JwtController");
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);

    }

}
