package org.example;

import org.example.cli.CLIApplication;
import org.example.task.Task;
import org.example.task.TaskManager;
import org.example.task.TaskModel;

public class Main {
    public static void main(String[] args) {
        TaskModel taskModel = new TaskModel();
        CLIApplication<Task> app = new TaskManager(taskModel);
        app.start();
    }
}
