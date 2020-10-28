package pl.edu.pjwstk.jazapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jazapi.model.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

}
