package iuh.fit.se.library.observer;

public class LibraryStaff implements Observer {
    private String id;
    private String name;
    private String position;

    public LibraryStaff(String id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }
    @Override
    public void update(String message) {
        System.out.println("Staff notification to " + name + " (" + position + "): " + message);

    }
}
