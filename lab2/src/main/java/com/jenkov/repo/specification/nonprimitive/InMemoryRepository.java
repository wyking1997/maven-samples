package com.jenkov.repo.specification.nonprimitive;

import java.util.List;

public interface InMemoryRepository<T> {

    public void add(T element);
    public void remove(T element);
    public boolean contains(T element);
    public List<T> getAll();
}
