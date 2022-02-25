package learn.abhi.projrestmaven.security.basicauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.abhi.projrestmaven.model.CarCreationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.UUID;

import static learn.abhi.projrestmaven.enums.CarBrand.HYUNDAI;

@SpringBootTest
@AutoConfigureMockMvc
public class BasicAuthTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void failWhenUnauthorized() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.post("/basicAuthCars"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "unauth", roles = {"unauth"})
    void failWhenForbidden() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.post("/basicAuthCars"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"CARUSER"})
    void successWhenUserCarUser() throws Exception{
        final String requestUuid = UUID.randomUUID().toString();

        CarCreationRequest createCarRequest = new CarCreationRequest(requestUuid, "i20", HYUNDAI, 5, LocalDateTime.now());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/basicAuthCars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCarRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @WithMockUser()
    void successWhenUserReadUser() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.get("/basicAuthCars"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void failWhenNotAdmin() throws Exception{
        final String requestUuid = UUID.randomUUID().toString();

        CarCreationRequest createCarRequest = new CarCreationRequest(requestUuid, "i20", HYUNDAI, 5, LocalDateTime.now());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/admin/basicAuthCars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCarRequest)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void successWhenAdmin() throws Exception {
        final String requestUuid = UUID.randomUUID().toString();

        CarCreationRequest createCarRequest = new CarCreationRequest(requestUuid, "i20", HYUNDAI, 5, LocalDateTime.now());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/admin/basicAuthCars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCarRequest)))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }



}
