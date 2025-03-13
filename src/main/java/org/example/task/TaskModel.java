package org.example.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TaskModel implements CRUD<Task> {
    private final Map<Integer, Task> tasks = new HashMap<>();

    @Override
    public Task create(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Task read(int id) {
        return tasks.get(id);
    }

    @Override
    public List<Task> read() {
        return tasks.values().stream().toList();
    }

    @Override
    public Task update(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public void delete(int id) {
        tasks.remove(id);
    }
}
