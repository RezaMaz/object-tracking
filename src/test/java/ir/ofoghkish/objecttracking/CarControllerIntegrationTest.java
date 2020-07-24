package ir.ofoghkish.objecttracking;


import com.fasterxml.jackson.databind.ObjectMapper;
import ir.ofoghkish.objecttracking.entity.Car;
import ir.ofoghkish.objecttracking.entity.enumeration.CarType;
import ir.ofoghkish.objecttracking.repository.CarDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectTrackingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class CarControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CarDAO carDAO;

    @Test
    public void testAddCar() throws Exception {
        Car car = new Car();
        car.setId(-1L);
        car.setType(CarType.Passenger);
        car.setPlateNumber("1A");

        mockMvc.perform(post("/api/v1/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isCreated());

        assertThat(carDAO.findAll().get(0).getPlateNumber()).isEqualTo("1A");
    }

}
