package pl.edu.pjwstk.jazapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jazapi.model.Car;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarCatalogueController {

    private List<Car> catalogue;

    public CarCatalogueController() {
        catalogue = new ArrayList<>();
        catalogue.add(new Car("Volvo", "1998"));
        catalogue.add(new Car("Renault", "2001"));
        catalogue.add(new Car("BMW", "2007"));
    }

    @GetMapping("/cars")
    public List<Car> getCarCatalogue() {
        return catalogue;
    }
}
