package iuh.fit.se.library.strategy;

import iuh.fit.se.library.model.Book;

import java.util.List;

public interface SearchStrategy {
    List<Book> search(List<Book> books, String criteria);
}
