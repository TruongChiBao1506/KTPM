package iuh.fit.se.library.factory;

import iuh.fit.se.library.model.Book;

import java.util.Map;

public interface BookFactory {
    Book createBook(String id, String title, String author, String genre, Map<String, Object> additionalInfo);

}
