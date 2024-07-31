package com.se.except.ex;

/**
 * @author Jinhua-Lee
 */
public class MyFirstException extends RuntimeException {

    public MyFirstException() {
        super();
    }

    public MyFirstException(String message) {
        super(message);
    }

    public MyFirstException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyFirstException(Throwable cause) {
        super(cause);
    }
}
