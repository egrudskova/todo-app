package org.example.cli.commands;

import com.rits.cloning.Cloner;
import lombok.Getter;
import lombok.Setter;
import org.example.task.CRUD;

import java.util.NoSuchElementException;

@Getter
public class EditCommand<T> extends Command<T> {
    private final String name = "edit";
    @Setter
    private CRUD<T> model;

    @Override
    public void execute(CommandSettings<T> settings) {
        try {
            int id = Integer.parseInt(settings.getFlags().get(CommandFlag.ID));
            String fieldName = settings.getFlags().get(CommandFlag.EDIT_FIELD);
            String selectedValue = settings.getFlags().get(CommandFlag.EDIT_VALUE);

            if (fieldName == null) {
                throw new NoSuchElementException("No field selected");
            }
            if (selectedValue == null) {
                throw new NoSuchElementException("No new value provided");
            }

            T editedObject = model.read(id);
            Cloner cloner = new Cloner();
            T objCopy = cloner.deepClone(editedObject);
            Command.setFieldValue(objCopy, fieldName, selectedValue);
            System.out.println("The item was updated successfully:" + model.update(objCopy));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
