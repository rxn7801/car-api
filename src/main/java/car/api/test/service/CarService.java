package car.api.test.service;

import car.api.test.dao.CarRepo;
import car.api.test.model.Car;
import car.api.test.model.CarRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Car service layer
 */
@Service
public class CarService {

    @Autowired
    private CarRepo carRepo;

    /**
     * To create car
     * @param request - create car request
     * @return - created car
     */
    public Car createCar(CarRequest request) {
        Car car = new Car();
        car.setMake(request.getMake());
        car.setModel(request.getModel());
        car.setColour(request.getColour());
        car.setYear(request.getYear());
        return carRepo.save(car);
    }


    /**
     * To get car based on id
     * @param id - car id
     * @return - car
     */
    public Car getCar(Long id) {
        Optional<Car> optionalCar = carRepo.findById(id);
        return optionalCar.orElse(null);
    }

    /**
     * To delete car based on id
     * @param carId - car id
     */
    public void deleteCar(Long carId) {
        if(carRepo.existsById(carId))
            carRepo.deleteById(carId);
    }

    /**
     * To update car based on id
     * if car doesn't exist, returns null/404
     * @param carId - car id
     * @param request - update request
     * @return - updated car
     */
    public Car updateCar(Long carId, CarRequest request) {

        final Optional<Car> optionalCar = carRepo.findById(carId);

        if (optionalCar.isEmpty())
            return null;

        Car carToUpdate = optionalCar.get();
        carToUpdate.setMake(request.getMake());
        carToUpdate.setModel(request.getModel());
        carToUpdate.setColour(request.getColour());
        carToUpdate.setYear(request.getYear());
        return carRepo.save(carToUpdate);
    }

    /**
     * To retrieve all cars
     * @return - list of cars
     */
    public List<Car> getAllCars() {
        return (List<Car>) carRepo.findAll();
    }
}
