package pl.edu.pjwstk.jazapi.dto;

import org.springframework.hateoas.server.core.Relation;
import pl.edu.pjwstk.jazapi.model.AddOn;
import pl.edu.pjwstk.jazapi.model.Car;
import pl.edu.pjwstk.jazapi.service.Identifiable;

import java.util.Set;
import java.util.stream.Collectors;

@Relation(itemRelation = "car", collectionRelation = "cars")
public class CarDTO implements Identifiable {

    private final Long id;
    private final String manufacturer;
    private final String model;
    private final Set<Long> addons;
    private final String yearOfProduction;

    public CarDTO(Car entity) {
        this.id = entity.getId();
        this.manufacturer = entity.getManufacturer();
        this.model = entity.getModel();
        this.addons = entity.getAddons().stream().map(AddOn::getId).collect(Collectors.toSet());
        this.yearOfProduction = entity.getYearOfProduction();
    }

    public CarDTO(Long id, String manufacturer, String model, Set<Long> addons, String yearOfProduction) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.addons = addons;
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public Set<Long> getAddons() {
        return addons;
    }

    public String getYearOfProduction() {
        return yearOfProduction;
    }
}
