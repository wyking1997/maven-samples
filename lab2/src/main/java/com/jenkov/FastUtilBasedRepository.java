package com.jenkov;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FastUtilBasedRepository<T> implements InMemoryRepository<T>{

    Set<T> set;

    public FastUtilBasedRepository() {
        this.set = new ObjectOpenHashSet<T>();
    }

    @Override
    public void add(T element) {
        set.add(element);
    }

    @Override
    public void remove(T element) {
        set.remove(element);
    }

    @Override
    public boolean contains(T element) {
        return set.contains(element);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(set);
    }
}
