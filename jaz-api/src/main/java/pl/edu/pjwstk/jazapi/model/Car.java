package pl.edu.pjwstk.jazapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.server.core.Relation;
import pl.edu.pjwstk.jazapi.service.Identifiable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String manufacturer;

    private String model;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AddOn> addons;

    @Column(name = "production_year")
    private String yearOfProduction;

    public Car() {
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(String yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Set<AddOn> getAddons() {
        return addons;
    }

    public void setAddons(Set<AddOn> addons) {
        this.addons = addons;
    }

    @Override
    public String toString() {
        return "Car[" +
                "id=" + id +
                "manufacturer=" + manufacturer +
                "model=" + model +
                "yearOfProduction=" + yearOfProduction +
                "addOns=" + addons +
                "]";
    }
}
