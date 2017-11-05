package com.jenkov.repo.implementation;

import com.jenkov.repo.specification.InMemoryRepository;

import java.util.ArrayList;
import java.util.List;

public class ArrayListBasedRepositpory<T> implements InMemoryRepository<T> {
    private List<T> arr;

    public ArrayListBasedRepositpory() {
        this.arr = new ArrayList<>();
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
        return arr;
    }
}
