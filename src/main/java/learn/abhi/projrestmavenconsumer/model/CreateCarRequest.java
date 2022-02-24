package learn.abhi.projrestmavenconsumer.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class CreateCarRequest {

    private String name;

    private CarBrand carBrand;

    private int seats;

    public enum CarBrand {

        HYUNDAI,
        VOLKSWAGEN

    }

}
