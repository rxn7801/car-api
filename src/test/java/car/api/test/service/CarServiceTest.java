package car.api.test.service;

import car.api.test.dao.CarRepo;
import car.api.test.model.Car;
import car.api.test.model.CarRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepo carRepo;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test for create car")
    void createCarTest() {

        CarRequest request = new CarRequest();
        request.setColour("blue");
        request.setMake("honda");
        request.setModel("civic");
        request.setYear("2020");

        Car car = new Car();
        car.setColour("blue");
        car.setMake("honda");
        car.setModel("civic");
        car.setYear("2020");

        when(carRepo.save(car)).thenReturn(car);

        assertEquals("honda",carService.createCar(request).getMake());

    }

    @Test
    @DisplayName("Test for get car")
    void getCarTest() {

        Car car = new Car();
        car.setId(1L);
        car.setColour("blue");
        car.setMake("honda");
        car.setModel("civic");
        car.setYear("2020");

        when(carRepo.findById(1L)).thenReturn(Optional.of(car));

        assertEquals("honda",carService.getCar(1L).getMake());
    }

    @Test
    @DisplayName("Test for get car when no car is found for the given id")
    void getCar_NoResultTest() {

        Car car = new Car();
        car.setId(2L);
        car.setColour("blue");
        car.setMake("honda");
        car.setModel("civic");
        car.setYear("2020");

        when(carRepo.findById(2L)).thenReturn(Optional.of(car));

        assertNull(carService.getCar(1L));
    }

    @Test
    @DisplayName("Test for delete car")
    void deleteCarTest() {
        when(carRepo.existsById(1L)).thenReturn(true);
        carService.deleteCar(1L);
        verify(carRepo, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Test for delete car when car id not exists")
    void deleteCar_NoResultsTest() {
        when(carRepo.existsById(1L)).thenReturn(false);
        carService.deleteCar(1L);
        verify(carRepo, times(0)).deleteById(1L);
    }

    @Test
    @DisplayName("Test for update car")
    void updateCarTest() {

        Car car = new Car();
        car.setId(1L);
        car.setColour("blue");
        car.setMake("honda");
        car.setModel("civic");
        car.setYear("2020");

        when(carRepo.findById(1L)).thenReturn(Optional.of(car));

        CarRequest request = new CarRequest();
        request.setColour("red");
        request.setMake("honda");
        request.setModel("civic");
        request.setYear("2020");

        when(carRepo.save(car)).thenReturn(car);

        assertEquals("red", carService.updateCar(1L, request).getColour());
    }

    @Test
    @DisplayName("Test for update car when car id is not found")
    void updateCar_NoResultTest() {

        when(carRepo.findById(1L)).thenReturn(Optional.empty());

        CarRequest request = new CarRequest();
        request.setColour("red");
        request.setMake("honda");
        request.setModel("civic");
        request.setYear("2020");

        assertNull(carService.updateCar(1L, request));
    }

    @Test
    @DisplayName("Test for get all cars")
    void getAllCarsTest() {

        Car car = new Car();
        car.setId(1L);
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

        when(carRepo.findAll()).thenReturn(Arrays.asList(car,car2));

        assertEquals(2,carService.getAllCars().size());
    }

}
