package learn.abhi.projrestmavenconsumer.controller;

import learn.abhi.projrestmavenconsumer.model.CarCreationResponse;
import learn.abhi.projrestmavenconsumer.model.CreateCarRequest;
import learn.abhi.projrestmavenconsumer.service.CarsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class CarsServiceController {

    private CarsService carsService;

    @PostMapping(value = "/cars-service", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    //todo validation of object
    @ResponseBody
    public ResponseEntity<CarCreationResponse> createCar(@RequestHeader(name = "Accept", defaultValue = "application/json") String accept,
                                                         @RequestBody @Validated CreateCarRequest createCarRequest) {
        CarCreationResponse response = carsService.addCar(createCarRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);

    }



}
