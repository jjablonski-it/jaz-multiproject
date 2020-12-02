package pl.edu.pjwstk.jazapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jazapi.model.AddOn;
import pl.edu.pjwstk.jazapi.model.Car;
import pl.edu.pjwstk.jazapi.service.CarService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/cars")
public class CarCatalogueController extends CrudController<Car> {

    public CarCatalogueController(CarService service) {
        super(service);
    }

    public Function<Car, Map<String, Object>> transformToDTO() {
        return car -> {
            var payload = new LinkedHashMap<String, Object>();
            payload.put("id", car.getId());
            payload.put("model", car.getModel());
            payload.put("manufacturer", car.getManufacturer());
            payload.put("production_year", car.getYearOfProduction());
            payload.put("addons", car.getAddons().stream().map(AddOn::getId));

            return payload;
        };
    }
}
