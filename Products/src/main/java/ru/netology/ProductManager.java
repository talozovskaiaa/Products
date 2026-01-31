package ru.netology;

public class ProductManager {
    private ProductRepository repository;

    /**
     * Конструктор, принимает репозиторий
     */
    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    /**
     * Метод добавления товара
     */
    public void add(Product product) {
        repository.save(product);
    }

    /**
     * Метод поиска по названию
     */
    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product product: repository.findAll()) {
            if (matches(product, text)) {
                Product[] temp = new Product[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    temp[i] = result[i];
                }
                temp[temp.length - 1] = product;
                result = temp;
            }
        }
        return result;
    }

    /**
     * Метод определения соответствия товара product запросу search
     */
    public boolean matches(Product product, String search) {
        if (product.getName().contains(search)) {
            return true;
        } else {
            return false;
        }
    }
}
