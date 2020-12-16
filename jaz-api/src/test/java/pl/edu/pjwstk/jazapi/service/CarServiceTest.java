package pl.edu.pjwstk.jazapi.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pjwstk.jazapi.model.Car;
import pl.edu.pjwstk.jazapi.repository.AddOnRepository;
import pl.edu.pjwstk.jazapi.repository.CarRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CarServiceTest {

    @Test
    public void basicTest() {
        CarRepository carRepository = Mockito.mock(CarRepository.class);
        AddOnRepository addOnRepository = Mockito.mock(AddOnRepository.class);
        CrudService<Car> carService = new CarService(carRepository, addOnRepository);

        Car car = new Car();
        car.setId(1L);
        car.setModel("126p");
        car.setManufacturer("Fiat");

        when(carRepository.findById(anyLong())).thenReturn(Optional.of(car));
        Car byId = carService.getById(1L);

        assertEquals(car.getModel(), byId.getModel());
    }
}
