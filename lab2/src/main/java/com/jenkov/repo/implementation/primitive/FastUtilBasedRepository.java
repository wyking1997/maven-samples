package com.jenkov.repo.implementation.primitive;

import com.jenkov.repo.specification.primitive.IntInMemoryRepository;
import it.unimi.dsi.fastutil.ints.AbstractIntList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntArraySet;

import java.util.Random;

public class FastUtilBasedRepository implements IntInMemoryRepository {

    private AbstractIntList list;

    public FastUtilBasedRepository() {
        this.list = new IntArrayList();
    }

    @Override
    public void add(int element) {
        list.add(element);
    }

    @Override
    public void remove(int element) {
        list.remove(element);
    }

    @Override
    public boolean contains(int element) {
        return list.contains(element);
    }

    @Override
    public int getRandomElement(Random random) {
        return list.get(random.nextInt(list.size()));
    }
}
