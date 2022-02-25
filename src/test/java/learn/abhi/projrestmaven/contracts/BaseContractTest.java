package learn.abhi.projrestmaven.contracts;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import learn.abhi.projrestmaven.controller.CarControllerBasicAuth;
import learn.abhi.projrestmaven.model.CarCreationRequest;
import learn.abhi.projrestmaven.model.CarCreationResponse;
import learn.abhi.projrestmaven.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static learn.abhi.projrestmaven.enums.CarBrand.HYUNDAI;

@WebMvcTest(value = CarControllerBasicAuth.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class BaseContractTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @BeforeEach
    public void setup(){
        Mockito.when(carService.createCar(Mockito.any(CarCreationRequest.class)))
                .thenAnswer(new Answer<CarCreationResponse>() {
                    @Override
                    public CarCreationResponse answer(InvocationOnMock invocationOnMock) throws Throwable {
                        final Object[] arguments = invocationOnMock.getArguments();
                        return new CarCreationResponse(((CarCreationRequest) arguments[0]).getRequestUuid(), "567e4567-e89b-14d3-a456-426614174000",
                                "i20", HYUNDAI, 5, LocalDateTime.now());
                    }
                });

        RestAssuredMockMvc.mockMvc(mockMvc);
//        RestAssuredMockMvc.standaloneSetup(new CarControllerBasicAuth(carServiceMock));
    }


}
