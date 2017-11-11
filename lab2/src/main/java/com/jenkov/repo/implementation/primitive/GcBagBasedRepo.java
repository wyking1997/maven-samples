package com.jenkov.repo.implementation.primitive;

import com.jenkov.repo.specification.nonprimitive.InMemoryRepository;
import com.jenkov.repo.specification.primitive.IntInMemoryRepository;
import org.eclipse.collections.api.list.primitive.IntList;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;

import java.util.List;
import java.util.Random;

public class GcBagBasedRepo implements IntInMemoryRepository {

    private IntArrayList ls = new IntArrayList();

    public GcBagBasedRepo() {
        ls = new IntArrayList();
    }

    @Override
    public void add(int element) {
        ls.add(element);
    }

    @Override
    public void remove(int element) {
        ls.remove(element);
    }

    @Override
    public boolean contains(int element) {
        return ls.contains(element);
    }

    @Override
    public int getRandomElement(Random random) {
        return ls.get(random.nextInt(ls.size()));
    }
}
