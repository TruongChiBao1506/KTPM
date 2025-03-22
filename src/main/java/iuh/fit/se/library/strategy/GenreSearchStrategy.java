package iuh.fit.se.library.strategy;

import iuh.fit.se.library.model.Book;

import java.util.ArrayList;
import java.util.List;

public class GenreSearchStrategy implements SearchStrategy {

    @Override
    public List<Book> search(List<Book> books, String criteria) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().toLowerCase().contains(criteria.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }
}
