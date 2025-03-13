package org.example.task;

import org.example.cli.CLIApplication;
import org.example.cli.commands.*;

import java.time.LocalDate;
import java.util.*;

public class TaskManager extends CLIApplication<Task> {
    String DEFAULT_FILTER_MODE = TaskField.STATUS.toString();
    String INFO_MESSAGE = """
            
            WELCOME TO TASK MANAGER
            
            Available commands:
                > add -arg1 <task_name> -arg2 <task_description> -arg3 <task_due_date> -- add task.
                > list -- list all tasks.
                > edit -id <task_id> -ef <edit_field> (name | description | status | due) -ev <edit_value> -- edit task.
                > delete -id <task_id> -- delete task.
                > filter -fv <filter_value> (todo | in progress | done) -- filter tasks by status.
                > sort -sf <sort_field> (status | due) -- sort tasks.
                > exit -- exit the program.
                > info -- displays this help message.
            
                Example of usage:
                > add -arg1 Task example -arg2 This is a task. -arg3 29.03.2025
                > edit -id 1 -ef status -ev done
                > delete -id 1
                > filter -fv todo
                > sort -sf due
            """;

    public TaskManager(CRUD<Task> model) {
        super(model);
    }

    @Override
    public void start() {
        List<Command<Task>> commandList = Arrays.asList(
                new AddCommand<>((args) -> new Task(
                        (String) args[0],
                        (String) args[1],
                        LocalDate.parse((CharSequence) args[2], Task.formatter))),
                new DeleteCommand<>(),
                new EditCommand<>(),
                new ExitCommand<>(),
                new FilterCommand<>(),
                new ListCommand<>(),
                new SortCommand<>(),
                new InfoCommand<>(INFO_MESSAGE));
        registerCommands(commandList);
        setDefaultFlags(new HashMap<>() {{
            put(CommandFlag.FILTER_FIELD, DEFAULT_FILTER_MODE);
        }});

        Scanner scanner = new Scanner(System.in);
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                this.processCommandLine(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
