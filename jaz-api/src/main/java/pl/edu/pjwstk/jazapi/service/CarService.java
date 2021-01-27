package pl.edu.pjwstk.jazapi.service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jazapi.model.AddOn;
import pl.edu.pjwstk.jazapi.model.Car;
import pl.edu.pjwstk.jazapi.repository.AddOnRepository;
import pl.edu.pjwstk.jazapi.repository.CarRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static pl.edu.pjwstk.jazapi.util.Utils.fallbackIfNull;

@Service
public class CarService extends CrudService<Car> {
    private final AddOnRepository addOnRepository;

    public CarService(CarRepository carRepository, AddOnRepository addOnRepository) {
        super(carRepository);
        this.addOnRepository = addOnRepository;
    }

    public List<Car> getByManufacturer(String manufacturer){
        return ((CarRepository) repository).getByManufacturer(manufacturer);
    }

    @Override
    public Car createOrUpdate(Car updateEntity) {
        if (updateEntity.getId() == null) {
            var addons = updateEntity.getAddons();
            updateEntity.setAddons(Collections.emptySet());
            Car insertedCar = repository.save(updateEntity);

            if (addons != null && addons.size() > 0) {
                addons.forEach(addon -> addon.setCar(insertedCar));
                addOnRepository.saveAll(addons);
            }

            return insertedCar;
        }

        Optional<Car> carInDb = repository.findById(updateEntity.getId());

        if (carInDb.isPresent()) {
            var dbEntity = carInDb.get();

            dbEntity.setManufacturer(fallbackIfNull(updateEntity.getManufacturer(), dbEntity.getManufacturer()));
            dbEntity.setModel(fallbackIfNull(updateEntity.getModel(), dbEntity.getModel()));
            dbEntity.setYearOfProduction(fallbackIfNull(updateEntity.getYearOfProduction(), dbEntity.getYearOfProduction()));
            var insertedCar = repository.save(dbEntity);

            Set<AddOn> addons = updateEntity.getAddons();

            if (addons != null && addons.size() > 0) {
                addons.forEach(addon -> addon.setCar(dbEntity));
                addOnRepository.saveAll(addons);
            }

            return insertedCar;
        } else {
            updateEntity = repository.save(updateEntity);

            return updateEntity;
        }
    }
}