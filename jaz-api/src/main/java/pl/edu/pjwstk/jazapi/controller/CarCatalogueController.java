package pl.edu.pjwstk.jazapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jazapi.model.Car;
import pl.edu.pjwstk.jazapi.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarCatalogueController {

    @Autowired
    private CarService carService;

    @GetMapping()
    public Iterable<Car> getCarCatalogue() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable("id") Long id) {
        return carService.getCarById(id);
    }

    @PostMapping
    public void addCar(@RequestBody Car car) {
        carService.createOrUpdateCar(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") Long id) {
        carService.deleteCarById(id);
    }
}
