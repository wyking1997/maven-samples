package com.jenkov.repo.implementation.nonprimitive;

import com.jenkov.repo.specification.nonprimitive.InMemoryRepository;
import com.koloboke.collect.map.hash.HashIntObjMap;
import com.koloboke.collect.map.hash.HashIntObjMaps;

import java.util.ArrayList;
import java.util.List;

public class KolobokeHashBasedRepo<T> implements InMemoryRepository<T> {

    private HashIntObjMap map = HashIntObjMaps.newMutableMap();

    //did not find something more, like primitive lists or primitive sets

    @Override
    public void add(T element) {
        map.put(element.hashCode(), element);
    }

    @Override
    public void remove(T element) {
        map.remove(element);
    }

    @Override
    public boolean contains(T element) {
        return map.containsValue(element);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(map.values());
    }
}
