package learn.abhi.projrestmavenconsumer.model;

import learn.abhi.projrestmavenconsumer.enums.CarBrand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CarCreationResponse {

    String requestUuid;

    String responseUuid;

    String name;

    CarBrand carBrand;

    int seats;

    LocalDateTime creationTime;


}
