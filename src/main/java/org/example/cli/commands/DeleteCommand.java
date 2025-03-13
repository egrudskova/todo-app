package org.example.cli.commands;

import lombok.Getter;
import lombok.Setter;
import org.example.task.CRUD;

@Getter
public class DeleteCommand<T> extends Command<T> {
    private final String name = "delete";
    @Setter
    private CRUD<T> model;

    @Override
    public void execute(CommandSettings<T> settings) {
        try {
            int id = Integer.parseInt(settings.getFlags().get(CommandFlag.ID));
            model.delete(id);
            System.out.println(
                    "The item was deleted successfully. Current list:\n" +
                            model.read());
        } catch (Exception e) {
            System.out.println("Task could not be deleted. Please try again.");
        }
    }
}
