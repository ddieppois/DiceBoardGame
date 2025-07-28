package com.ddieppois.diceboard.diceboardgame.exceptions;

public class DiceBoardGamePropertiesException extends RuntimeException {
    public DiceBoardGamePropertiesException(String message) {
        super(message);
    }

    public DiceBoardGamePropertiesException(String message, Throwable cause) {
        super(message, cause);
    }
}
