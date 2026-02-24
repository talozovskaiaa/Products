package ru.netology;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book extends Product {
    protected String author;
    protected String booksName;
    protected int price;

    public Book(int id, String name, int price, String booksName, String author) {
        super(id, name, price);
        this.booksName = booksName;
        this.author = author;
        this.price = price;
    }

    @Override
    public void use() {
        System.out.println("Читаем книгу: " + name);
    }

    /**
     * Метод для получения автора
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Метод для получения названия книги
     */
    public String getBooksName() {
        return booksName;
    }

    /**
     * Метод для получения цены
     */
    public int getPrice() {
        return price;
    }

    public static void main(String[] args) {

    Book book1 = new Book(1, "Java Basics", 25, "Java Basics 1.0", "author1");
    Book book2 = new Book(2, "PHP Basics", 26, "PHP Basics", "author2");
    Book book3 = new Book(3, "Javascript Basics", 27, "Javascript Basics", "author3");
    Book book4 = new Book(3, "Python Basics", 28, "Python Basics", "author4");

    List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        Collections.sort(books, new BookPriceComparator());

        for (Book book : books) {
            System.out.println(book);
        }
    }
}

