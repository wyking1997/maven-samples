package com.jenkov;

import com.koloboke.collect.map.hash.HashIntObjMaps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KolobokeHashBasedRepo<T> implements InMemoryRepository<T> {

    private Map<Integer, T> map = HashIntObjMaps.newMutableMap();

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
