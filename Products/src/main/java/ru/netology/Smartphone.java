package ru.netology;

public class Smartphone extends Product {
    protected String smartphonesName;
    protected String manufacturer;

    public Smartphone(int id, String name, int price, String smartphonesName, String manufacturer) {
        super(id, name, price);
        this.smartphonesName = smartphonesName;
        this.manufacturer = manufacturer;
    }

    /**
     * Метод для получения названия смартфона
     */
    public String getSmartphonesName() {
        return smartphonesName;
    }

    /**
     * Метод для получения названия компании
     */
    public String getManufacturer() {
        return manufacturer;
    }
}
