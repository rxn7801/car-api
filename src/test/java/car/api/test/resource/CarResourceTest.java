package car.api.test.resource;

import car.api.test.model.Car;
import car.api.test.model.CarRequest;
import car.api.test.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@WebMvcTest(CarResource.class)
class CarResourceTest {

    @MockBean
    private CarService carService;

    @InjectMocks
    private CarResource carResource;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test for create API")
    void createCarTest() throws Exception {
        CarRequest request = new CarRequest();
        request.setColour("blue");
        request.setMake("honda");
        request.setModel("civic");
        request.setYear("2020");

        when(carService.createCar(request)).thenReturn(new Car());

        mvc.perform(MockMvcRequestBuilders.post("/car")
                .content(convertToJson(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Test for get API")
    void getCarTest() throws Exception {
        Car car = new Car();
        car.setColour("blue");
        car.setMake("honda");
        car.setModel("civic");
        car.setYear("2020");

        when(carService.getCar(1L)).thenReturn(car);

        mvc.perform(MockMvcRequestBuilders.get("/car/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(convertToJson(car)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test for update API")
    void updateCarTest() throws Exception {
        Car updatedCar = new Car();
        updatedCar.setColour("blue");
        updatedCar.setMake("honda");
        updatedCar.setModel("civic");
        updatedCar.setYear("2021");

        CarRequest request = new CarRequest();
        request.setColour("red");
        request.setMake("honda");
        request.setModel("civic");
        request.setYear("2020");

        when(carService.updateCar(1L, request)).thenReturn(updatedCar);

        mvc.perform(MockMvcRequestBuilders.put("/car/1")
                .content(convertToJson(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(convertToJson(updatedCar)))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Test for delete API")
    void deleteCarTest() throws Exception {

        mvc.perform(MockMvcRequestBuilders.delete("/car/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Test for get all cars API")
    void getAllCarsTest() throws Exception {
        Car car = new Car();
        car.setColour("blue");
        car.setMake("honda");
        car.setModel("civic");
        car.setYear("2020");

        Car car2 = new Car();
        car2.setId(1L);
        car2.setColour("red");
        car2.setMake("honda");
        car2.setModel("civic");
        car2.setYear("2020");

        when(carService.getAllCars()).thenReturn(Arrays.asList(car, car2));

        mvc.perform(MockMvcRequestBuilders.get("/car")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(convertToJson(Arrays.asList(car, car2))))
                .andExpect(status().isOk());
    }


    private static String convertToJson(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
