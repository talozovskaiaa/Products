import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.*;

public class BooksManagerTest {

    private ProductManager productManager;
    private ProductRepository repository;
    private Book item1, item2, item3, item4, item5, item6, item7, item8;

    @BeforeEach
    public void sutup() {
        repository = new ProductRepository();
        productManager = new ProductManager(repository);

        item1 = FakerUtils.generateBook();
        item2 = FakerUtils.generateBook();
        item3 = FakerUtils.generateBook();
        item4 = FakerUtils.generateBook();
        item5 = FakerUtils.generateBook();
        item6 = FakerUtils.generateBook();
        item7 = FakerUtils.generateBook();
        item8 = FakerUtils.generateBook();
    }

    @Test
    public void addingBook() {
        productManager.add(item1);
        productManager.add(item2);
        productManager.add(item3);
        productManager.add(item4);
        productManager.add(item5);
        productManager.add(item6);
        productManager.add(item7);
        productManager.add(item8);

        Product[] expected = {item1, item2, item3, item4, item5, item6, item7, item8};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void matchesBookTest() {
        Product product = item1;

        boolean expected = true;
        boolean actual = productManager.matches(product, product.getName());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchByBookNameTest() {
        productManager.add(item1);
        productManager.add(item2);
        productManager.add(item3);

        String searchText = item2.getName();

        Product[] expected = {item2};
        Product[] actual = productManager.searchBy(searchText);

        Assertions.assertArrayEquals(expected, actual);
    }

}
