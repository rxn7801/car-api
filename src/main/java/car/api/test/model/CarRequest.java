package car.api.test.model;

import lombok.Data;

/**
 * Car dto
 */
@Data
public class CarRequest {

    private String make;
    private String model;
    private String colour;
    private String year;
}
