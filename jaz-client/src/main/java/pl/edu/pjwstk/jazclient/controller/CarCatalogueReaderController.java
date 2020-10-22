package pl.edu.pjwstk.jazclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.edu.pjwstk.jazclient.model.Car;

import java.util.Arrays;
import java.util.List;

@RestController
public class CarCatalogueReaderController {

    private final String URI = "http://jazapi:11111/cars";

    @GetMapping("/readCatalogue")
    public List<Car> getCatalogue() {
        RestTemplate template = new RestTemplate();

        Car[] catalogue = template.getForObject(URI, Car[].class);

        return Arrays.asList(catalogue);
    }
}
