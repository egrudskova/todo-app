package org.example.cli.commands;

import lombok.Getter;
import lombok.Setter;
import org.example.task.CRUD;

import java.util.Map;

@Getter
public class InfoCommand<T> extends Command<T> {
    private final String name = "info";
    @Setter
    private CRUD<T> model;
    private final String message;

    public InfoCommand(String message) {
        System.out.println(message);
        this.message = message;
    }

    @Override
    public void execute(Map<CommandFlag, String> flags) {
        System.out.println(message);
    }
}
