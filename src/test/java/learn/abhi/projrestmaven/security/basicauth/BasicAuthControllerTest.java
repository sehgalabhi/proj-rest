package learn.abhi.projrestmaven.security.basicauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.abhi.projrestmaven.config.BasicSecurityConfiguration;
import learn.abhi.projrestmaven.controller.basic.BasicAdminCarController;
import learn.abhi.projrestmaven.controller.basic.BasicCarController;
import learn.abhi.projrestmaven.model.CarCreationRequest;
import learn.abhi.projrestmaven.model.CarCreationResponse;
import learn.abhi.projrestmaven.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.UUID;

import static learn.abhi.projrestmaven.enums.CarBrand.HYUNDAI;

@WebMvcTest({BasicCarController.class, BasicAdminCarController.class})
@Import(BasicSecurityConfiguration.class)
public class BasicAuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

    }

    @Test
    void failWhenUnauthorized() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.post("/basic/cars"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "unauth", roles = {"unauth"})
    void failWhenForbidden() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.post("/basic/cars"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"CARUSER"})
    void successWhenUserCarUser() throws Exception{
        final String requestUuid = UUID.randomUUID().toString();

        CarCreationRequest createCarRequest = new CarCreationRequest(requestUuid, "i20", HYUNDAI, 5, LocalDateTime.now());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/basic/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCarRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser()
    void successWhenUserReadUser() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.get("/basic/cars"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = {"CARUSER"})
    void failWhenNotAdmin() throws Exception{
        final String requestUuid = UUID.randomUUID().toString();

        CarCreationRequest createCarRequest = new CarCreationRequest(requestUuid, "i20", HYUNDAI, 5, LocalDateTime.now());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/admin/basic/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCarRequest)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void successWhenAdmin() throws Exception {
        final String requestUuid = UUID.randomUUID().toString();

        CarCreationRequest createCarRequest = new CarCreationRequest(requestUuid, "i20", HYUNDAI, 5, LocalDateTime.now());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/admin/basic/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCarRequest)))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }



}
