package org.example.task;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class Task {
    private static int counter = 0;
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
    @Setter(AccessLevel.NONE)
    private int id = ++counter;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private LocalDate due;
    private TaskStatus status = TaskStatus.TODO;

    public void setStatus(String status) {
        TaskStatus newStatus = TaskStatus.findStatus(status);
        if (newStatus == null) {
            throw new IllegalArgumentException();
        }
        this.status = newStatus;
    }

    public void setDue(String due) {
        this.due = LocalDate.parse(due, formatter);
    }

    @Override
    public String toString() {
        return "\nTask" + id + ":" +
                "\n name='" + name + '\'' +
                "\n description='" + description + '\'' +
                "\n due=" + due +
                "\n status=" + status;
    }
}
