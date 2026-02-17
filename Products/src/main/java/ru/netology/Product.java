package ru.netology;

public abstract class Product implements Usable {
    protected int id;
    protected String name;
    protected int price;

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Метод для получения ID
     */
    public int getId() {
        return id;
    }

    /**
     * Метод для получения названия
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для получения цены
     */
    public int getPrice() {
        return price;
    }

    @Override
    public void use() {
        System.out.println("Продукт " + name + " используется");
    }
}
