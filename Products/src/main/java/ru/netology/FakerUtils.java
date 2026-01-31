package ru.netology;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class FakerUtils {

    private static Faker faker = new Faker(new Locale("ru"));

    private FakerUtils() {
    }

    public static String generateRandomProducts() {
        String[] names = {
                "products1", "products2", "products3", "products4",
                "products5", "products6", "products7", "products8",
                "products9", "products10", "products11", "products12",
                "products14", "products15", "products16", "products17",
                "products18", "products19", "products20", "products21"
        };
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }

    public static int generateRandomID() {
        return faker.number().numberBetween(1, 1000);
    }

    public static int generatePrice() {
        return faker.number().numberBetween(100, 1000);
    }

    public static String generateBooksName() {
        return faker.book().title();
    }

    public static String generateAuthor() {
        return faker.book().author();
    }

    public static String generateSmartphonesName() {
        return faker.app().name();
    }

    public static String generateManufacturer() {
        return faker.company().name();
    }

    public static Book generateBook() {
        return new Book(
                generateRandomID(),
                generateRandomProducts(),
                generatePrice(),
                generateBooksName(),
                generateAuthor()
        );
    }

    public static Smartphone generateSmartphone() {
        return new Smartphone(
                generateRandomID(),
                generateRandomProducts(),
                generatePrice(),
                generateSmartphonesName(),
                generateManufacturer()
        );
    }
}
