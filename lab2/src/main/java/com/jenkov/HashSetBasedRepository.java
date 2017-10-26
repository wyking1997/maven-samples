package com.jenkov;

import java.util.HashSet;
import java.util.Set;

public class HashSetBasedRepository<T> implements InMemoryRepository<T> {
    private Set<T> arr;

    public HashSetBasedRepository() {
        this.arr = new HashSet<>();
    }

    @Override
    public void add(T element) {
        arr.add(element);
    }

    @Override
    public void remove(T element) {
        arr.remove(element);
    }

    @Override
    public boolean contains(T element) {
        return (arr.contains(element));
    }
}
