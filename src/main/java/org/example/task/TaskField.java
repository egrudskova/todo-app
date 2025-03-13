package org.example.task;

public enum TaskField {
    DUE("due"),
    STATUS("status");

    private final String name;

    TaskField(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
