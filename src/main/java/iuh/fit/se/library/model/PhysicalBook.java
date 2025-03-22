package iuh.fit.se.library.model;

public class PhysicalBook extends Book{
    private String shelfLocation;

    public PhysicalBook(String id, String title, String author, String genre, String shelfLocation) {
        super(id, title, author, genre);
        this.shelfLocation = shelfLocation;
    }

    public String getShelfLocation() { return shelfLocation; }
}
