package learn.abhi.projrestmavenconsumer.model;


import learn.abhi.projrestmavenconsumer.enums.CarBrand;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class CreateCarRequest {

    String requestUuid;

    String name;

    CarBrand carBrand;

    int seats;

    LocalDateTime requestedTime;



}
