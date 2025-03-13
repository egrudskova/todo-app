package org.example.cli.commands;

import lombok.Getter;
import lombok.Setter;
import org.example.task.CRUD;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Getter
public class SortCommand<T> extends Command<T> {
    private final String name = "sort";
    @Setter
    private CRUD<T> model;

    @Override
    public void execute(final Map<CommandFlag, String> flags) {
        try {
            List<T> objectsList = model.read();
            String fieldName = flags.get(CommandFlag.SORT_FIELD);

            if (fieldName == null) {
                throw new NoSuchElementException("No field selected");
            }

            Comparator<T> sortComparator = Comparator.comparing(el -> Command.getFieldValue(el, fieldName).toString());
            List<T> sortedList = objectsList.stream().sorted(sortComparator).toList();

            System.out.println("The sorted list:\n" + sortedList);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("The list could not be sorted. Please try again.");
        }
    }
}
