import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.*;

public class SmartphoneRepositoryTest {

    private ProductRepository productRepository;
    private Smartphone smartphone1, smartphone2, smartphone3, smartphone4, smartphone5, smartphone6;

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();

        smartphone1 = FakerUtils.generateSmartphone();
        smartphone2 = FakerUtils.generateSmartphone();
        smartphone3 = FakerUtils.generateSmartphone();
        smartphone4 = FakerUtils.generateSmartphone();
        smartphone5 = FakerUtils.generateSmartphone();
        smartphone6 = FakerUtils.generateSmartphone();
    }

    @Test
    public void removingTheBookByIdTest() {
        Smartphone[] smartphones = {smartphone1, smartphone2, smartphone3, smartphone4, smartphone5, smartphone6};
        for (Smartphone smartphone : smartphones) {
            productRepository.save(smartphone);
        }
        productRepository.removeById(smartphone3.getId());

        Product[] expected = {smartphone1, smartphone2, smartphone4, smartphone5, smartphone6};
        Product[] actual = productRepository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addingNewBookTest() {
        productRepository.save(smartphone1);
        productRepository.save(smartphone2);
        productRepository.save(smartphone3);
        productRepository.save(smartphone4);
        productRepository.save(smartphone5);
        productRepository.save(smartphone6);

        Product[] expected = {smartphone1, smartphone2, smartphone3, smartphone4, smartphone5, smartphone6};
        Product[] actual = productRepository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllBookTest() {
        Smartphone[] smartphones = {smartphone1, smartphone2, smartphone3, smartphone4, smartphone5, smartphone6};
        for (Smartphone smartphone : smartphones) {
            productRepository.save(smartphone);
        }
        Product[] expected = {smartphone1, smartphone2, smartphone3, smartphone4, smartphone5, smartphone6};
        Product[] actual = productRepository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}
