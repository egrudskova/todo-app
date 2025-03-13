package org.example.cli.commands;

import java.util.Arrays;

public enum CommandFlag {
    ID("id"),
    FILTER_FIELD("ff"),
    FILTER_VALUE("fv"),
    EDIT_FIELD("ef"),
    EDIT_VALUE("ev"),
    SORT_FIELD("sf"),
    ARG_1("arg1"),
    ARG_2("arg2"),
    ARG_3("arg3");

    private final String name;

    CommandFlag(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static CommandFlag findFlag(final String flagName) {
        return Arrays.stream(CommandFlag.values())
                .filter((el) -> el.toString().equals(flagName))
                .findFirst()
                .orElse(null);
    }
}
