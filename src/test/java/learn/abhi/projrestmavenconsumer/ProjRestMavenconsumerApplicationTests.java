package learn.abhi.projrestmavenconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.abhi.projrestmavenconsumer.model.CreateCarRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static learn.abhi.projrestmavenconsumer.enums.CarBrand.HYUNDAI;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureStubRunner(ids = "learn.abhi:proj-rest-maven-provider:+:stubs:8080", stubsMode = StubsMode.LOCAL)
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
        final String requestUuid = UUID.randomUUID().toString();

        CreateCarRequest createCarRequest = new CreateCarRequest(requestUuid, "i20", HYUNDAI, 5, LocalDateTime.now());

        mockMvc.perform(
                post("/cars-service")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(createCarRequest)
                        ).accept(APPLICATION_JSON_VALUE))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString())         )
                .andExpectAll(
                        status().isCreated()
                        , content().contentType(APPLICATION_JSON)
                        , jsonPath("$.requestUuid").value(requestUuid)
                        , jsonPath("$.name").value("i20")
                        , jsonPath("$.carBrand").value(HYUNDAI.name())
                        , jsonPath("$.seats").value(5)
                        , jsonPath("$.responseUuid").value("567e4567-e89b-14d3-a456-426614174000")
                        //todo fix
//                        , jsonPath("$.creationTime").exists()

                );


    }

}
