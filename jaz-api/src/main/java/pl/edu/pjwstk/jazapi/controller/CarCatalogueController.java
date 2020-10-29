package pl.edu.pjwstk.jazapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jazapi.model.Car;
import pl.edu.pjwstk.jazapi.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarCatalogueController {

    @Value("${train.count}")
    private int trainCount;

    @Autowired
    private CarService carService;

    @GetMapping
    public Integer getCarCatalogue() {
        return trainCount;
    }

    @PostMapping
    public void addCar(@RequestBody Car car) {
        carService.addNewCar(car);
    }

    @PutMapping
    public void updateCar(@RequestBody Car car) {
        carService.updateCar(car);
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public void deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
    }
}
