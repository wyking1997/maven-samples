package com.example1;

public class IntegerComputer implements Computer<Integer> {

    @Override
    public Integer performOpperation(Integer a, Integer b, String opperator) {

        return OpperationFactory.getOperation(opperator).compute(a, b);
    }
}
