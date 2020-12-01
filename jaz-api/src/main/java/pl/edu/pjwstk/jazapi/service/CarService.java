package pl.edu.pjwstk.jazapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jazapi.model.Car;
import pl.edu.pjwstk.jazapi.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pl.edu.pjwstk.jazapi.util.Utils.fallbackIfNull;

@Component
public class CarService {

    private final CarRepository repository;

    public CarService(CarRepository carRepository) {
        this.repository = carRepository;
    }

    public List<Car> getAllCars() {
        Iterable<Car> cars = repository.findAll();
        var carList = new ArrayList<Car>();

        cars.forEach(carList::add);

        return carList;
    }

    public Car getCarById(Long id) {
        Optional<Car> car = repository.findById(id);

        return car.orElse(null);
    }

    public Car createOrUpdateCar(Car updateEntity) {
        if (updateEntity.getId() == null) {
            return repository.save(updateEntity);
        }

        Optional<Car> carInDb = repository.findById(updateEntity.getId());

        if (carInDb.isPresent()) {
            Car dbEntity = carInDb.get();

            dbEntity.setManufacturer(fallbackIfNull(updateEntity.getManufacturer(), dbEntity.getManufacturer()));
            dbEntity.setModel(fallbackIfNull(updateEntity.getModel(), dbEntity.getModel()));
            dbEntity.setYearOfProduction(fallbackIfNull(updateEntity.getYearOfProduction(), dbEntity.getYearOfProduction()));

            dbEntity = repository.save(dbEntity);

            return dbEntity;
        } else {
            updateEntity = repository.save(updateEntity);

            return updateEntity;
        }
    }

    public void deleteCarById(Long id) {
        Optional<Car> carInDb = repository.findById(id);

        if (carInDb.isPresent()) {
            repository.deleteById(id);
        }
    }
}