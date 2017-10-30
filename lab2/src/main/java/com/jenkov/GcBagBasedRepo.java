package com.jenkov;

import org.eclipse.collections.impl.bag.mutable.HashBag;

import java.util.List;

public class GcBagBasedRepo<T> implements InMemoryRepository<T> {

    HashBag<T> bag;

    public GcBagBasedRepo() {
        this.bag = HashBag.newBag();
    }

    @Override
    public void add(T element) {
        bag.add(element);
    }

    @Override
    public void remove(T element) {
        bag.remove(element);
    }

    @Override
    public boolean contains(T element) {
        return bag.contains(element);
    }

    @Override
    public List<T> getAll() {
        return bag.toList();
    }
}
