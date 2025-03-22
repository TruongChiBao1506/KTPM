package iuh.fit.se.library.factory;

import iuh.fit.se.library.model.Book;
import iuh.fit.se.library.model.PhysicalBook;

import java.util.Map;

public class PhysicalBookFactory implements BookFactory{
    @Override
    public Book createBook(String id, String title, String author, String genre, Map<String, Object> additionalInfo) {
        String shelfLocation = (String) additionalInfo.get("shelfLocation");
        return new PhysicalBook(id, title, author, genre, shelfLocation);
    }
}
