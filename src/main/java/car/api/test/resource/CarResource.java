package car.api.test.resource;

import car.api.test.model.Car;
import car.api.test.model.CarRequest;
import car.api.test.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Car resource containing APIs
 */
@RestController
@RequestMapping("/car")
public class CarResource {

    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody CarRequest request) {
        return new ResponseEntity<>(carService.createCar(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") Long carId) {
        return new ResponseEntity<>(carService.getCar(carId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCar() {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable("id") Long carId) {
        carService.deleteCar(carId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") Long carId, @RequestBody CarRequest request) {
        Car car = carService.updateCar(carId, request);
        if (null == car)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(car, HttpStatus.OK);
    }
}
