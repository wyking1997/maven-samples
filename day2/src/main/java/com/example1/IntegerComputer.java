package com.example1;

public class IntegerComputer implements Computer<Integer> {
    private IntegerOpperation integerSum;
    private IntegerOpperation integerDifference;
    private IntegerOpperation integerMultiplication;

    public IntegerComputer() {
        this.integerSum = new IntegerSum();
        this.integerDifference = new IntegerDifference();
        this.integerMultiplication = new IntegerMultiplication();
    }

    @Override
    public Integer performSum(Integer op1, Integer op2) {
        return this.integerSum.compute(op1, op2);
    }

    @Override
    public Integer performDifference(Integer op1, Integer op2) {
        return this.integerDifference.compute(op1, op2);
    }

    @Override
    public Integer performMultiplication(Integer op1, Integer op2) {
        return this.integerMultiplication.compute(op1, op2);
    }
}
