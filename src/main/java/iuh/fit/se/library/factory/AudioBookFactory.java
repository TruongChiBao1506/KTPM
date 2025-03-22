package iuh.fit.se.library.factory;

import iuh.fit.se.library.model.AudioBook;
import iuh.fit.se.library.model.Book;

import java.util.Map;

public class AudioBookFactory implements BookFactory{

    @Override
    public Book createBook(String id, String title, String author, String genre, Map<String, Object> additionalInfo) {
        int durationMinutes = (int) additionalInfo.get("durationMinutes");
        String narrator = (String) additionalInfo.get("narrator");
        return new AudioBook(id, title, author, genre, durationMinutes, narrator);
    }
}
