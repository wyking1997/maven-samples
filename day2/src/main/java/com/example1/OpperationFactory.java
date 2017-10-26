package com.example1;

public class OpperationFactory {

    private static IntegerSumComputer integerSumComputer = new IntegerSumComputer();
    private static IntegerDifferenceComputer integerDifferenceComputer = new IntegerDifferenceComputer();
    private static IntegerMultiplicationComputer integerMultiplicationComputer = new IntegerMultiplicationComputer();

    public static IntegerOpperationComputer getOperation(String opperand){
        switch (opperand){
            case "+": return integerSumComputer;
            case "-": return integerDifferenceComputer;
            case "*": return integerMultiplicationComputer;
        }
        return null;
    }
}
