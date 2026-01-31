package ru.netology;

public class Smartphone extends Product {
    protected String smartphonesName;
    protected String manufacturer;

    public Smartphone(int id, String name, int price, String smartphonesName, String manufacturer) {
        super(id, name, price);
        this.smartphonesName = smartphonesName;
        this.manufacturer = manufacturer;
    }

    public String getSmartphonesName() {
        return smartphonesName;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
