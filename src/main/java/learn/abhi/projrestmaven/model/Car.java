package learn.abhi.projrestmaven.model;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
public class Car {

    //todo add generation
    @Id
    private long id;

    String name;

    CarBrand carBrand;

    int seats;

    public enum CarBrand {

        HYUNDAI,
        VOLKSWAGEN

    }
}
