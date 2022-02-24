package learn.abhi.projrestmaven.model;

import learn.abhi.projrestmaven.enums.CarBrand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CarCreationRequest {

    String requestUuid;

    String name;

    CarBrand carBrand;

    int seats;

    LocalDateTime requestedTime;


}
