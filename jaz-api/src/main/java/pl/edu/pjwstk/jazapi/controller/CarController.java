package pl.edu.pjwstk.jazapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jazapi.dto.CarDTO;
import pl.edu.pjwstk.jazapi.model.Car;
import pl.edu.pjwstk.jazapi.repository.CarRepository;
import pl.edu.pjwstk.jazapi.service.CarService;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/cars")
public class CarController extends CrudController<Car, CarDTO> {

    public CarController(CarService service) {
        super(service);
    }

    @GetMapping("/m/{manufacturer}")
    public ResponseEntity<CollectionModel<EntityModel<CarDTO>>> getByManufacturer(@PathVariable("manufacturer") String manufacturer) {
        try {
            var items = ((CarService) service).getByManufacturer(manufacturer);
            var itemsWithLinks = items.stream()
                    .map(obj -> transformToDTO().apply(obj))
                    .map(obj -> addLinksForItem().apply(obj))
                    .collect(Collectors.toList());

            var payload = addLinksForCollection().apply(itemsWithLinks);

            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
                linkTo(methodOn(CarController.class).getAll()).withRel("cars"),
                linkTo(methodOn(CarController.class).getByManufacturer(car.getManufacturer())).withRel("manufacturer")
        );
    }
}
