package com.example.demo.Exception;

public class ObjectNotFound extends RuntimeException {
    public ObjectNotFound(String message) {
        super("Object Not Found: " + message);
    }
}
//c