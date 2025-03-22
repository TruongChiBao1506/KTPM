package iuh.fit.se.library.factory;

import iuh.fit.se.library.model.Book;
import iuh.fit.se.library.model.EBook;

import java.util.Map;

public class EBookFactory implements BookFactory{

    @Override
    public Book createBook(String id, String title, String author, String genre, Map<String, Object> additionalInfo) {
        String fileFormat = (String) additionalInfo.get("fileFormat");
        int fileSizeMB = (int) additionalInfo.get("fileSizeMB");
        return new EBook(id, title, author, genre, fileFormat, fileSizeMB);
    }
}
