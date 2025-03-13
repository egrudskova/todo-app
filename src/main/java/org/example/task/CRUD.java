package org.example.task;

import java.util.List;

public interface CRUD<T> {
    T create(T obj);

    List<T> read();

    T read(int id);

    T update(T obj);

    void delete(int id);
}
