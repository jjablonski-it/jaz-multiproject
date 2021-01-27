package pl.edu.pjwstk.jazapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jazapi.dto.CarDTO;
import pl.edu.pjwstk.jazapi.model.Car;
import pl.edu.pjwstk.jazapi.service.CarService;

import java.util.Collection;
import java.util.function.Function;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/cars")
public class CarController extends CrudController<Car, CarDTO> {

    public CarController(CarService service) {
        super(service);
    }

    @Override
    public Function<Car, CarDTO> transformToDTO() {
        return CarDTO::new;
    }

    @Override
    public Function<Collection<EntityModel<CarDTO>>, CollectionModel<EntityModel<CarDTO>>> addLinksForCollection() {
        return cars -> CollectionModel.of(cars,
                linkTo(methodOn(CarController.class).getAll()).withRel("cars"));
    }

    @Override
    public Function<CarDTO, EntityModel<CarDTO>> addLinksForItem() {
        return car -> EntityModel.of(car,
                linkTo(methodOn(CarController.class).getById(car.getId())).withSelfRel(),
                linkTo(methodOn(CarController.class).getAll()).withRel("cars"));
    }
}
