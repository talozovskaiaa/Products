import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.Book;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UseTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void usePrintsCorrectMessage() {
        Book p = new Book(1, "Каштановый человечек", 500, "Каштановый человечек!", "Свейструп Сорен");

        p.use();

        String expected = "Читаем книгу: Каштановый человечек" + System.lineSeparator();
        assertEquals(expected, outContent.toString());
    }

}
