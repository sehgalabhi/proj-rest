package learn.abhi.projrestmaven.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {

    String name;

    CarBrand carBrand;

    int seats;

    public enum CarBrand {

        HYUNDAI,
        VOLKSWAGEN

    }
}
