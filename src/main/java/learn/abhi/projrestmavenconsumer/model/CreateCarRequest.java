package learn.abhi.projrestmavenconsumer.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class CreateCarRequest {

    String name;

    CarBrand carBrand;

    int seats;

    public enum CarBrand {

        HYUNDAI,
        VOLKSWAGEN

    }

}
