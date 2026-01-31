import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.*;

public class SmartphoneManagerTest {

    private ProductManager productManager;
    private ProductRepository repository;
    private Smartphone smartphone1, smartphone2, smartphone3, smartphone4, smartphone5, smartphone6;

    @BeforeEach
    public void setup() {
        repository = new ProductRepository();
        productManager = new ProductManager(repository);

        smartphone1 = FakerUtils.generateSmartphone();
        smartphone2 = FakerUtils.generateSmartphone();
        smartphone3 = FakerUtils.generateSmartphone();
        smartphone4 = FakerUtils.generateSmartphone();
        smartphone5 = FakerUtils.generateSmartphone();
        smartphone6 = FakerUtils.generateSmartphone();
    }

    @Test
    public void addingBook() {
        productManager.add(smartphone1);
        productManager.add(smartphone2);
        productManager.add(smartphone3);
        productManager.add(smartphone4);
        productManager.add(smartphone5);
        productManager.add(smartphone6);

        Product[] expected = {smartphone1, smartphone2, smartphone3, smartphone4, smartphone5, smartphone6};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void matchesBookTest() {
        Product product = smartphone1;

        boolean expected = true;
        boolean actual = productManager.matches(product, product.getName());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchByBookNameTest() {
        productManager.add(smartphone1);
        productManager.add(smartphone2);
        productManager.add(smartphone3);

        String searchText = smartphone2.getName();

        Product[] expected = {smartphone2};
        Product[] actual = productManager.searchBy(searchText);

        Assertions.assertArrayEquals(expected, actual);
    }
}
