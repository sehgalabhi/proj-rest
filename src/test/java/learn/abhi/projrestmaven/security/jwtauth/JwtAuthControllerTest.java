package learn.abhi.projrestmaven.security.jwtauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.abhi.projrestmaven.config.JwtSecurityConfiguration;
import learn.abhi.projrestmaven.controller.jwt.JwtAdminCarController;
import learn.abhi.projrestmaven.controller.jwt.JwtCarController;
import learn.abhi.projrestmaven.controller.jwt.TokenController;
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
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.UUID;

import static learn.abhi.projrestmaven.enums.CarBrand.HYUNDAI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({JwtCarController.class, JwtAdminCarController.class, TokenController.class})
@Import(JwtSecurityConfiguration.class)
public class JwtAuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarService carService;

    @BeforeEach
    public void setup() {
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
    void successWhenAuthorized() throws Exception{
        final MvcResult mvcResult = this.mockMvc.perform(post("/token")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("abhijwt", "password")))
                .andExpect(status().isOk())
                .andReturn();

        final String token = mvcResult.getResponse().getContentAsString();

        System.out.println(token);

        this.mockMvc.perform(get("/jwt/cars")
        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }



    @Test
    void failWhenNoJwtTokenUnauthorized() throws Exception{

        this.mockMvc.perform(post("/jwt/cars"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void failWhenJwtRequestInvalidUnauthorized() throws Exception{
        final MvcResult mvcResult = this.mockMvc.perform(post("/token")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("abhijwt", "invalid")))
                .andExpect(status().isUnauthorized())
                .andReturn();


    }

    @Test
    void failWhenForbidden() throws Exception{
        final MvcResult mvcResult = this.mockMvc.perform(post("/token")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("abhijwt", "password")))
                .andExpect(status().isOk())
                .andReturn();

        final String token = mvcResult.getResponse().getContentAsString();

        System.out.println(token);

        this.mockMvc.perform(post("/admin/jwt/cars")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());
    }

    @Test
    void successWhenUserCarUser() throws Exception{
        final String requestUuid = UUID.randomUUID().toString();

        CarCreationRequest createCarRequest = new CarCreationRequest(requestUuid, "i20", HYUNDAI, 5, LocalDateTime.now());

        this.mockMvc.perform(post("/jwt/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCarRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void successWhenUserReadUser() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.get("/jwt/cars"))
                .andExpect(status().isOk());
    }

    @Test
    void failWhenNotAdmin() throws Exception{
        final String requestUuid = UUID.randomUUID().toString();

        CarCreationRequest createCarRequest = new CarCreationRequest(requestUuid, "i20", HYUNDAI, 5, LocalDateTime.now());

        this.mockMvc.perform(post("/admin/jwt/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCarRequest)))
                .andExpect(status().isForbidden());
    }

    @Test
    void successWhenAdmin() throws Exception {

        final MvcResult mvcResult = this.mockMvc.perform(post("/token")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("abhijwt", "password")))
                .andExpect(status().isOk())
                .andReturn();

        final String token = mvcResult.getResponse().getContentAsString();

        System.out.println(token);

        this.mockMvc.perform(post("/admin/jwt/cars")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());

        final String requestUuid = UUID.randomUUID().toString();

        CarCreationRequest createCarRequest = new CarCreationRequest(requestUuid, "i20", HYUNDAI, 5, LocalDateTime.now());

        this.mockMvc.perform(post("/admin/jwt/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCarRequest)))
                .andExpect(status().isCreated());
    }
}
