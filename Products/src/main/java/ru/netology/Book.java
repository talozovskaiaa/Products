package ru.netology;

public class Book extends Product {
    protected String author;
    protected String booksName;

    public Book(int id, String name, int price, String booksName, String author) {
        super(id, name, price);
        this.booksName = booksName;
        this.author = author;
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
}
