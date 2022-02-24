package learn.abhi.projrestmaven.model;

import learn.abhi.projrestmaven.enums.CarBrand;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
public class Car {

    //todo add generation
    @Id
    private long id;

    String name;

    CarBrand carBrand;

    int seats;

    String requestUuid;

    String responseUuid;


    LocalDateTime creationTime;

}
