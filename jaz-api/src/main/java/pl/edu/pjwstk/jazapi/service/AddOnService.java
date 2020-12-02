package pl.edu.pjwstk.jazapi.service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jazapi.model.AddOn;
import pl.edu.pjwstk.jazapi.repository.AddOnRepository;
import pl.edu.pjwstk.jazapi.repository.CarRepository;

import java.util.Optional;

import static pl.edu.pjwstk.jazapi.util.Utils.fallbackIfNull;

@Service
public class AddOnService extends CrudService<AddOn> {
    public AddOnService(AddOnRepository repository) {
        super(repository);
    }

    @Override
    public AddOn createOrUpdate(AddOn updateEntity) {
        if (updateEntity.getId() == null) {
            return repository.save(updateEntity);
        }

        Optional<AddOn> addonInDb = repository.findById(updateEntity.getId());

        if (addonInDb.isPresent()) {
            var dbEntity = addonInDb.get();

            dbEntity.setCar(fallbackIfNull(updateEntity.getCar(), dbEntity.getCar()));
            dbEntity.setName(fallbackIfNull(updateEntity.getName(), dbEntity.getName()));
            dbEntity.setPrice(fallbackIfNull(updateEntity.getPrice(), dbEntity.getPrice()));

            return repository.save(dbEntity);
        } else {
            updateEntity = repository.save(updateEntity);

            return updateEntity;
        }
    }
}