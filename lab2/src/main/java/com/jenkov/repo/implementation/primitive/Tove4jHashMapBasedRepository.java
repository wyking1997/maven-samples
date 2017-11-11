package com.jenkov.repo.implementation.primitive;

import com.jenkov.repo.specification.primitive.IntInMemoryRepository;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;

import java.util.Random;

public class Tove4jHashMapBasedRepository implements IntInMemoryRepository {

    private TIntList list;

    public Tove4jHashMapBasedRepository() {
        this.list = new TIntArrayList();
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
