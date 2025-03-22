package iuh.fit.se.library.observer;

public class User implements Observer {
    private String id;
    private String name;
    private String email;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    @Override
    public void update(String message) {
        System.out.println("Notification to user " + name + ": " + message);
    }
}
