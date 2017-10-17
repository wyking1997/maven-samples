package com.jenkov;

public interface InMemoryRepository<T> {

    public void add(T element);
    public void remove(T element);
    public boolean contains(T element);
}
