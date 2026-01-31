import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.*;

public class BooksRepositoryTest {

    private ProductRepository productRepository;
    private Book item1, item2, item3, item4, item5, item6, item7, item8;

    @BeforeEach
    public void setUp() {
        productRepository = new ProductRepository();

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
    public void removingTheBookByIdTest() {
        Book[] books = {item1, item2, item3, item4, item5, item6, item7, item8};
        for (Book book : books) {
            productRepository.save(book);
        }
        productRepository.removeById(item3.getId());

        Product[] expected = {item1, item2, item4, item5, item6, item7, item8};
        Product[] actual = productRepository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addingNewBookTest() {
        productRepository.save(item1);
        productRepository.save(item2);
        productRepository.save(item3);
        productRepository.save(item4);
        productRepository.save(item5);
        productRepository.save(item6);
        productRepository.save(item7);
        productRepository.save(item8);

        Product[] expected = {item1, item2, item3, item4, item5, item6, item7, item8};
        Product[] actual = productRepository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllBookTest() {
        Book[] books = {item1, item2, item3, item4, item5, item6, item7, item8};
        for (Book book : books) {
            productRepository.save(book);
        }
        Product[] expected = {item1, item2, item3, item4, item5, item6, item7, item8};
        Product[] actual = productRepository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}
