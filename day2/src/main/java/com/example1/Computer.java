package com.example1;

public interface Computer<T> {
    public T performSum(T op1, T op2);
    public T performDifference(T op1, T op2);
    public T performMultiplication(T op1, T op2);
}
