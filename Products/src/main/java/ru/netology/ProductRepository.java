package ru.netology;

public class ProductRepository {
    private Product[] products = new Product[0];

    public void save(Product product) {
        Product[] newProducts = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            newProducts[i] = products[i];
        }
        newProducts[newProducts.length - 1] = product;
        products = newProducts;
    }

    /**
     * метод для удаления одного из элементов массива
     */
    public void removeById(int id) {
        Product[] newProducts = new Product[products.length - 1];
        int a = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                newProducts[a] = product;
                a++;
            }
        }
        products = newProducts;
    }

    public Product[] findAll() {
        return products;
    }
}
