package iuh.fit.se.library.singleton;

import iuh.fit.se.library.model.Book;
import iuh.fit.se.library.observer.Observer;
import iuh.fit.se.library.observer.User;
import iuh.fit.se.library.strategy.SearchStrategy;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private static Library instance;
    private List<Book> books;
    private List<Observer> observers;

    private Library() {
        books = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static synchronized Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
        notifyObservers("Sách mới đã được thêm: " + book.getTitle());
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public Book borrowBook(String bookId, User user) {
        for (Book book : books) {
            if (book.getId().equals(bookId) && book.isAvailable()) {
                book.setAvailable(false);
                return book;
            }
        }
        return null;
    }

    public void returnBook(String bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                book.setAvailable(true);
                break;
            }
        }
    }

    public List<Book> searchBooks(SearchStrategy strategy, String criteria) {
        return strategy.search(books, criteria);
    }

    // Observer Pattern methods
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
