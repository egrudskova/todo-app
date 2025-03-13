package org.example.cli;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.cli.commands.Command;
import org.example.cli.commands.CommandFlag;
import org.example.task.CRUD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public abstract class CLIApplication<T> {
    private final Map<String, Command<T>> commands = new HashMap<>();
    private final CRUD<T> model;
    @Setter
    private Map<CommandFlag, String> defaultFlags = new HashMap<>();

    public void registerCommands(final List<Command<T>> commandList) {
        commandList.forEach((command) -> {
            command.setModel(model);
            commands.put(command.getName(), command);
        });
    }

    private Command<T> parseCommand(final String input) {
        String commandName = input.trim().split(" ")[0];
        Command<T> command = commands.get(commandName);
        if (command == null) {
            throw new IllegalArgumentException("Unknown command: " + commandName);
        }
        return command;
    }

    private Map<CommandFlag, String> parseFlags(final String input) {
        Map<CommandFlag, String> flags = new HashMap<>(defaultFlags);
        String[] inputArr = input.trim().split(" ");
        int i = 1;
        while (i < inputArr.length - 1) {
            if (inputArr[i].startsWith("-")) {
                String flagName = inputArr[i].substring(1);
                StringBuilder flagValue = new StringBuilder();
                i++;
                while (i < inputArr.length && !inputArr[i].startsWith("-")) {
                    flagValue.append(" ").append(inputArr[i]);
                    i++;
                }
                flags.put(CommandFlag.findFlag(flagName), String.valueOf(flagValue).trim());
            }
        }
        return flags;
    }

    public void processCommandLine(final String input) {
        try {
            if (input.isBlank()) {
                throw new IllegalArgumentException("Empty command line");
            }
            Command<T> command = parseCommand(input);
            Map<CommandFlag, String> flags = parseFlags(input);
            command.execute(flags);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    public abstract void start();
}
