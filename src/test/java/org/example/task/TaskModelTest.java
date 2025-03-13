package org.example.task;

import com.rits.cloning.Cloner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskModelTest {
    static TaskModel taskModel;
    static String name = "Example Task";
    static String description = "Example description";
    static LocalDate date = LocalDate.now();
    TaskStatus defaultStatus = TaskStatus.TODO;


    @BeforeAll
    static void setUp() {
        taskModel = new TaskModel();
        taskModel.create(new Task(name, description, date));
        taskModel.create(new Task(name, description, date));
    }
    @Test
    void create() {
        assertAll(
                "Create task assertions",
                () -> assertEquals(1, taskModel.read(1).getId(), "Task id mismatch"),
                () -> assertEquals(2, taskModel.read(2).getId(), "Task id wasn't incremented"),
                () -> assertEquals(name, taskModel.read(1).getName(), "Task name mismatch"),
                () -> assertEquals(description, taskModel.read(1).getDescription(), "Task description mismatch"),
                () -> assertEquals(date, taskModel.read(1).getDue(), "Task due mismatch"),
                () -> assertEquals(defaultStatus, taskModel.read(1).getStatus(), "Task status mismatch")
        );
    }

    @Test
    void read() {
        assertAll(
                "Create read assertions",
                () -> assertEquals(2, taskModel.read().size(), "Wrong number of tasks"),
                () -> assertNotNull(taskModel.read(), "No tasks found"),
                () -> assertNotNull(taskModel.read(1), "No task found"),
                () -> assertTrue(taskModel.read(1) instanceof Task, "Type mismatch")
        );
    }

    @Test
    void update() {
        Task task = taskModel.read(1);
        Cloner cloner = new Cloner();
        Task taskCopy = cloner.deepClone(task);
        String newName = "New name";
        String newDescription = "New description";
        taskCopy.setName(newName);
        taskCopy.setDescription(newDescription);
        taskCopy.setStatus(TaskStatus.DONE.toString());
        taskModel.update(taskCopy);
        assertAll(
                "Create update assertions",
                () -> assertEquals(newName, taskModel.read(1).getName(), "Task name mismatch"),
                () -> assertEquals(newDescription, taskModel.read(1).getDescription(), "Task description mismatch"),
                () -> assertEquals(TaskStatus.DONE, taskModel.read(1).getStatus(), "Task status mismatch")
        );
    }

    @Test
    void delete() {
        taskModel.create(new Task(name, description, date));
        taskModel.delete(3);
        assertAll(
                "Create delete assertions",
                () -> assertEquals(2, taskModel.read().size(), "Wrong number of tasks"),
                () -> assertNull(taskModel.read(3), "Task not deleted")
        );
    }
}