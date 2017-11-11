package com.jenkov.repo.implementation.primitive;

import com.jenkov.repo.specification.nonprimitive.InMemoryRepository;
import gnu.trove.set.hash.THashSet;
import gnu.trove.set.hash.TIntHashSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Tove4jHashMapBasedRepository<T> implements InMemoryRepository<T> {

    private Set<T> set;
    private TIntHashSet set2;

    public Tove4jHashMapBasedRepository() {
        this.set = new THashSet<>();
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
