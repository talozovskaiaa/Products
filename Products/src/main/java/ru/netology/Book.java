package ru.netology;

public class Book extends Product {
    protected String author;
    protected String booksName;

    public Book(int id, String name, int price, String booksName, String author) {
        super(id, name, price);
        this.booksName = booksName;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getBooksName() {
        return booksName;
    }
}
