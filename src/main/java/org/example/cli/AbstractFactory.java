package org.example.cli;

public interface AbstractFactory<T> {
    T newObject(Object... args);
}