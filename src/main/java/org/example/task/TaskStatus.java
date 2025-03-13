package org.example.task;


import java.util.Arrays;

public enum TaskStatus {
    TODO("todo"),
    IN_PROGRESS("in progress"),
    DONE("done");

    private final String name;

    TaskStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static TaskStatus findStatus(String flagName) {
        return Arrays.stream(TaskStatus.values())
                .filter((el) -> el.toString().equals(flagName))
                .findFirst()
                .orElse(null);
    }
}
