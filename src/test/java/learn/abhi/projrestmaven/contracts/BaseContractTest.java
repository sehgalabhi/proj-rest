package learn.abhi.projrestmaven.contracts;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import learn.abhi.projrestmaven.controller.CarController;
import learn.abhi.projrestmaven.model.CarCreationRequest;
import learn.abhi.projrestmaven.model.CarCreationResponse;
import learn.abhi.projrestmaven.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.time.LocalDateTime;
import java.util.UUID;

import static learn.abhi.projrestmaven.enums.CarBrand.HYUNDAI;

public class BaseContractTest {

    @BeforeEach
    public void setup(){
        final CarService carServiceMock = Mockito.mock(CarService.class);

        Mockito.when(carServiceMock.createCar(Mockito.any(CarCreationRequest.class)))
                .thenAnswer(new Answer<CarCreationResponse>() {
                    @Override
                    public CarCreationResponse answer(InvocationOnMock invocationOnMock) throws Throwable {
                        final Object[] arguments = invocationOnMock.getArguments();
                        return new CarCreationResponse(((CarCreationRequest) arguments[0]).getRequestUuid(), "567e4567-e89b-14d3-a456-426614174000",
                                "i20", HYUNDAI, 5, LocalDateTime.now());
                    }
                });
        RestAssuredMockMvc.standaloneSetup(new CarController(carServiceMock));
    }


}
