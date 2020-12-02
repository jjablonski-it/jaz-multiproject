package pl.edu.pjwstk.jazapi.model;

import pl.edu.pjwstk.jazapi.service.DbEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "addons")
public class AddOn implements DbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    public AddOn() {
    }

    public AddOn(Long id, String name, BigDecimal price, Car car) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        String carId = car != null ? String.valueOf(car.getId()) : "";
        return "AddOn[" +
                "id=" + id +
                "name=" + name +
                "price=" + price +
                "car=" + carId +
                "]";
    }
}
