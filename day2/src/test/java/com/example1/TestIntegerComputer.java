package com.example1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestIntegerComputer {

    private Computer<Integer> integerComputer;

    @Before
    public void setUp(){
        this.integerComputer = new IntegerComputer();
    }

    @Test
    public void testSum(){
        for (int i = 0; i < 100; i += 2)
            for (int j = 0; j < 100; j += 2)
                assertEquals((long)this.integerComputer.performSum(i, j), i + j);
    }

    @Test
    public void testDifference(){
        for (int i = 0; i < 100; i += 2)
            for (int j = 0; j < 100; j += 2)
                assertEquals((long)this.integerComputer.performDifference(i, j), i - j);
    }

    @Test
    public void testMultiplication(){
        for (int i = 0; i < 100; i += 2)
            for (int j = 0; j < 100; j += 2)
                assertEquals((long)this.integerComputer.performMultiplication(i, j), i * j);
    }
}

//parametized junit tests
