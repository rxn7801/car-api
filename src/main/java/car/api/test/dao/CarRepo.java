package car.api.test.dao;


import car.api.test.model.Car;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for dao
 */
public interface CarRepo extends CrudRepository<Car, Long> { }
