package org.example.cli.commands;

import lombok.Getter;
import lombok.Setter;
import org.example.task.CRUD;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@Getter
public class FilterCommand<T> extends Command<T> {
    private final String name = "filter";
    @Setter
    private CRUD<T> model;

    @Override
    public void execute(Map<CommandFlag, String> flags) {
        try {
            List<T> objectsList = model.read();
            String fieldName = flags.get(CommandFlag.FILTER_FIELD);
            String selectedValue = flags.get(CommandFlag.FILTER_VALUE);

            if (fieldName == null) {
                throw new NoSuchElementException("No field selected");
            }

            if (selectedValue == null) {
                throw new NoSuchElementException("No filter option selected");
            }

            List<T> filteredList = objectsList.stream().filter((el) -> {
                String fieldValue = Command.getFieldValue(el, fieldName).toString();
                return fieldValue.equals(selectedValue);
            }).toList();

            System.out.println("The filtered list:" + filteredList);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("The list could not be filtered. Please try again.");
        }
    }
}
