package com.example;

public class Calculator
{
    public Calculator() {
    }

    public Long add(Integer op1, Integer op2) throws ReflectiveOperationException {
        return (op1.longValue()+op2.longValue());
    }
}
