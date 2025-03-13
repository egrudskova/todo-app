package org.example.cli;

import org.example.cli.commands.ExitCommand;
import org.example.cli.commands.InfoCommand;
import org.example.task.Task;
import org.example.task.TaskModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CLIApplicationTest {
    CLIApplication<Task> application;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));

        application = new CLIApplication<>(new TaskModel()) {
            @Override
            public void start() {
            }
        };
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void registerCommands() {
        String stringOutput = "info message";
        application.registerCommands(List.of(new InfoCommand<>(stringOutput)));
        assertAll("Create register command assertions", () -> {
            assertEquals(stringOutput, outContent.toString().trim(), "The command is not registered");
        });
    }

    @Test
    void processCommandLine() {
        application.registerCommands(List.of(new ExitCommand<>()));
        EndOfProgramException exception = assertThrows(EndOfProgramException.class, () ->
                application.processCommandLine("exit"));

        assertAll("Create process command line assertions", () -> {
            assertEquals("Exit command was executed. Bye", exception.getMessage(), "The command is not executed");
        });
    }
}

