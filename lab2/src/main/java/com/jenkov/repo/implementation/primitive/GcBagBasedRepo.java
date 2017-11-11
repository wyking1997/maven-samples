package com.jenkov.repo.implementation.primitive;

import com.jenkov.repo.specification.nonprimitive.InMemoryRepository;
import org.eclipse.collections.api.list.primitive.IntList;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;

import java.util.List;

public class GcBagBasedRepo<T> implements InMemoryRepository<T> {

    private HashBag<T> bag;
    private IntList x = new IntArrayList();

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
