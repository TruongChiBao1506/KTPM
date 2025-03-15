package iuh.fit.se.bai2.task_management;

public class TeamMember implements TaskObserver{
    private String name;
    private String role;

    public TeamMember(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public void update(Task task) {
        System.out.printf("%s %s received notification: Task '%s' has been updated to status %s\n",
                role, name, task.getName(), task.getStatus());
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
