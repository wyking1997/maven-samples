package com.example1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OpperationFactory {

    private static final Map<String, IntegerOpperationComputer> oppComputers;
    static {
        Map<String, IntegerOpperationComputer> myMap = new HashMap<>();
        myMap.put("+", new IntegerSumComputer());
        myMap.put("-", new IntegerDifferenceComputer());
        myMap.put("*", new IntegerMultiplicationComputer());
        oppComputers = Collections.unmodifiableMap(myMap);
    }

    public static IntegerOpperationComputer getOperation(String opperand){
        return oppComputers.get(opperand);
    }
}