package iuh.fit.se.library.model;

public class EBook extends Book {
    private String fileFormat;
    private int fileSizeMB;

    public EBook(String id, String title, String author, String genre, String fileFormat, int fileSizeMB) {
        super(id, title, author, genre);
        this.fileFormat = fileFormat;
        this.fileSizeMB = fileSizeMB;
    }

    public String getFileFormat() { return fileFormat; }
    public int getFileSizeMB() { return fileSizeMB; }
}
