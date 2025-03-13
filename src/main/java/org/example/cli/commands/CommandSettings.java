package org.example.cli.commands;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@Getter
public class CommandSettings<T> {
    private T obj;
    private Map<CommandFlag, String> flags;

    public CommandSettings(T obj) {
        this.obj = obj;
    }

    public CommandSettings(Map<CommandFlag, String> flags) {
        this.flags = flags;
    }
}

