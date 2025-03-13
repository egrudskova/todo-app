package org.example.cli.commands;

import lombok.Getter;
import lombok.Setter;
import org.example.task.CRUD;

import java.util.List;
import java.util.Map;

@Getter
public class ListCommand<T> extends Command<T> {
    private final String name = "list";
    @Setter
    private CRUD<T> model;

    @Override
    public void execute(final Map<CommandFlag, String> flags) {
        try {
            List<T> objectsList = model.read();
            System.out.println("There are " + objectsList.size() + " items:");
            System.out.println(objectsList);
        } catch (Exception e) {
            System.out.println("Task could not be listed. Please try again.");
        }
    }
}
