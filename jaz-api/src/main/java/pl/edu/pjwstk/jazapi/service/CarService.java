package pl.edu.pjwstk.jazapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jazapi.model.Car;
import pl.edu.pjwstk.jazapi.repository.CarRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository repository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.repository = carRepository;
    }

    public List<Car> getAllCars() {
        Iterable<Car> cars = repository.findAll();
        var carList = new ArrayList<Car>();

        cars.forEach(carList::add);

        if (carList.size() > 0) {
            return carList;
        } else {
            return Collections.emptyList();
        }
    }

    public Car getCarById(Long id) {
        Optional<Car> car = repository.findById(id);

        return car.orElse(null);
    }

    public Car createOrUpdateCar(Car entity) {

        if (entity.getId() == null) {
            return repository.save(entity);
        }

        Optional<Car> carInDb = repository.findById(entity.getId());

        if (carInDb.isPresent()) {
            Car newEntity = carInDb.get();
            newEntity.setManufacturer(entity.getManufacturer());
            newEntity.setYearOfProduction(entity.getYearOfProduction());

            newEntity = repository.save(newEntity);

            return newEntity;
        } else {
            entity = repository.save(entity);

            return entity;
        }
    }

    public void deleteCarById(Long id) {
        Optional<Car> carInDb = repository.findById(id);

        if (carInDb.isPresent()) {
            repository.deleteById(id);
        }
    }
}
