package org.example.cli.commands;

import lombok.Getter;
import lombok.Setter;
import org.example.task.CRUD;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;

@Getter
public abstract class Command<T> {
    private String name;
    @Setter
    private CRUD<T> model;

    private static Method getObjectMethod(final Object obj, final String fieldName, final String methodType) {
        return Arrays.stream(obj.getClass().getMethods())
                .filter((method) -> method.getName().startsWith(methodType) && method.getName().toLowerCase().endsWith(fieldName))
                .findFirst()
                .orElse(null);
    }

    protected static Object getFieldValue(final Object obj, final String fieldName) {
        try {
            Method fieldGetter = getObjectMethod(obj, fieldName, "get");
            return fieldGetter.invoke(obj);
        } catch (Exception e) {
            throw new NoSuchElementException("The field" + fieldName + " does not exist.");
        }
    }

    protected static void setFieldValue(final Object obj, final String fieldName, final Object value) {
        try {
            Method fieldSetter = getObjectMethod(obj, fieldName, "set");
            fieldSetter.invoke(obj, value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new NoSuchElementException("The field " + fieldName + " does not exist.");
        }
    }

    public abstract void execute(final Map<CommandFlag, String> flags);
}
