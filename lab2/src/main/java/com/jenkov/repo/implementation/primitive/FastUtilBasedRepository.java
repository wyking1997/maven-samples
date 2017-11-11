package com.jenkov.repo.implementation.primitive;

import com.jenkov.repo.specification.nonprimitive.InMemoryRepository;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.ints.AbstractIntSet;
import it.unimi.dsi.fastutil.ints.IntArraySet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FastUtilBasedRepository<T> implements InMemoryRepository<T> {

    private Set<T> set;
    private AbstractIntSet set2 = new IntArraySet();

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
