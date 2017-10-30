package com.jenkov;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetBasedRepository<T> implements InMemoryRepository<T> {
    private Set<T> arr;

    public TreeSetBasedRepository() {
        this.arr = new TreeSet<>();
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

    @Override
    public List<T> getAll() {
        return new ArrayList<>(arr);
    }
}
