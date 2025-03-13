package org.example.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TaskModel implements CRUD<Task> {
    private final Map<Integer, Task> tasks = new HashMap<>();

    @Override
    public Task create(final Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Task read(final int id) {
        return tasks.get(id);
    }

    @Override
    public List<Task> read() {
        return tasks.values().stream().toList();
    }

    @Override
    public Task update(final Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public void delete(final int id) {
        tasks.remove(id);
    }
}
