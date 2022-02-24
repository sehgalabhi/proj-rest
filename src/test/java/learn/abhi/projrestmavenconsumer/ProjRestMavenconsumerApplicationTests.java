package learn.abhi.projrestmavenconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.abhi.projrestmavenconsumer.model.CreateCarRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static learn.abhi.projrestmavenconsumer.model.CreateCarRequest.CarBrand.HYUNDAI;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureStubRunner(ids = "learn.abhi:proj-rest-maven:+:stubs:8080", stubsMode = StubsMode.LOCAL)
class ProjRestMavenconsumerApplicationTests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void contextLoads() {
    }

    @Test
    public void shouldCreateCar() throws Exception {

        CreateCarRequest createCarRequest = new CreateCarRequest("i20", HYUNDAI, 5);

        mockMvc.perform(
                post("/cars-service")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(createCarRequest)
                        ).accept(APPLICATION_JSON_VALUE))
                .andExpectAll(
                        status().isCreated()
                        , content().contentType(APPLICATION_JSON)
                        //todo match content
                );


    }

}
