package com.example.simpleapp;

@FunctionalInterface
public interface MyConsumer {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept() throws Exception;
}