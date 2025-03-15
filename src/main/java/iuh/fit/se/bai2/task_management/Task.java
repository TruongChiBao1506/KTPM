package iuh.fit.se.bai2.task_management;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private String name;
    private String description;
    private TaskStatus status;
    private List<TaskObserver> observers = new ArrayList<>();

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = TaskStatus.TODO;
    }

    public void attach(TaskObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void detach(TaskObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (TaskObserver observer : observers) {
            observer.update(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        if (this.status != status) {
            TaskStatus oldStatus = this.status;
            this.status = status;
            System.out.printf("Task '%s' status changed: %s -> %s\n", name, oldStatus, status);
            notifyObservers();
        }
    }
}
