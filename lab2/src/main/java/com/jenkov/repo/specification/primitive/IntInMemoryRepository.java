package com.jenkov.repo.specification.primitive;

import java.util.List;
import java.util.Random;

public interface IntInMemoryRepository {

    public void add(int element);
    public void remove(int position);
    public boolean contains(int element);
    public int getRandomElement(Random random);
    public int getSize();
}
