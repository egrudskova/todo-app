package org.example.cli.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.cli.AbstractFactory;
import org.example.task.CRUD;

import java.util.Map;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Getter
public class AddCommand<T> extends Command<T> {
    private final String name = "add";
    @Setter
    private CRUD<T> model;
    private final AbstractFactory<T> factory;

    private Object[] collectArgValues(Map<CommandFlag, String> flags) {
        return flags.keySet().stream()
                .filter(e -> e.toString().startsWith("arg"))
                .sorted()
                .map(flags::get)
                .toArray();
    }

    @Override
    public void execute(Map<CommandFlag, String> flags) {
        try {
            Object[] args = collectArgValues(flags);
            T newObject = factory.newObject(args);

            if (newObject == null) {
                throw new NoSuchElementException();
            }

            T createdObj = model.create(newObject);
            System.out.println("The item was added successfully:" + createdObj);

        } catch (Exception e) {
            System.out.println("Task could not be added.");
        }
    }
}
