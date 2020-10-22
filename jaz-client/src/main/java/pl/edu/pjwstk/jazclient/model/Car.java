package pl.edu.pjwstk.jazclient.model;

public class Car {

    private String manufacturer;
    private String yearOfProduction;

    public Car() {
    }

    public Car(String manufacturer, String yearOfProduction) {
        this.manufacturer = manufacturer;
        this.yearOfProduction = yearOfProduction;
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
}
