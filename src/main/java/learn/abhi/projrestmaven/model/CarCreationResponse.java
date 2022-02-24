package learn.abhi.projrestmaven.model;

import learn.abhi.projrestmaven.enums.CarBrand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CarCreationResponse {

    String requestUuid;

    String responseUuid;

    String name;

    CarBrand carBrand;

    int seats;

    LocalDateTime creationTime;


}
