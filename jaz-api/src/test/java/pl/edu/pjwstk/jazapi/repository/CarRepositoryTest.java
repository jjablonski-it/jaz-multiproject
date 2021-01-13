package pl.edu.pjwstk.jazapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.edu.pjwstk.jazapi.model.Car;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void basicTest() {
        Car car = new Car();
        car.setId(1L);
        car.setModel("126p");
        car.setManufacturer("Fiat");
        car.setYearOfProduction("1984");
        carRepository.save(car);

        Car fromDb = carRepository.getOne(1L);

        assertEquals(car.getId(), fromDb.getId());
    }
}