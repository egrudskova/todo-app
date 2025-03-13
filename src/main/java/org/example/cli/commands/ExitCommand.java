package org.example.cli.commands;

import lombok.Getter;
import lombok.Setter;
import org.example.cli.EndOfProgramException;
import org.example.task.CRUD;

import java.util.Map;

@Getter
public class ExitCommand<T> extends Command<T> {
    private final String name = "exit";
    @Setter
    private CRUD<T> model;

    @Override
    public void execute(Map<CommandFlag, String> flags) {
        throw new EndOfProgramException("Exit command was executed. Bye");
    }
}
