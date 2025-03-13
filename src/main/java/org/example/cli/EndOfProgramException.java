package org.example.cli;

public class EndOfProgramException extends RuntimeException {
    public EndOfProgramException(String message) {
        super(message);
    }
}
