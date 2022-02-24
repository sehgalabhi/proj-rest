package learn.abhi.projrestmaven.contracts;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import learn.abhi.projrestmaven.controller.CarController;
import learn.abhi.projrestmaven.model.Car;
import learn.abhi.projrestmaven.model.CarRequest;
import learn.abhi.projrestmaven.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static learn.abhi.projrestmaven.model.Car.CarBrand.HYUNDAI;

public class BaseContractTest {

    @BeforeEach
    public void setup(){
        final CarService carServiceMock = Mockito.mock(CarService.class);
        Mockito.when(carServiceMock.createCar(Mockito.any(CarRequest.class))).thenReturn(new Car(1, "i20", HYUNDAI, 5));
        RestAssuredMockMvc.standaloneSetup(new CarController(carServiceMock));
    }


}
