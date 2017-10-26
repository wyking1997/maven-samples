package com.example1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestIntegerComputer {

    private Computer<Integer> integerComputer;
    private Integer op1;
    private Integer op2;
    private Integer result;
    private String opperator;

    public TestIntegerComputer(Integer op1, Integer op2, String opperator, Integer result) {
        this.op1 = op1;
        this.op2 = op2;
        this.result = result;
        this.opperator = opperator;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 0, 0, "+", 0 }, { 2, 4, "+", 6 }, { 10, 120, "+", 130 }, { 0, 312, "+", 312 }, { 312, 312, "+", 624 },
                { 0, 10, "-", -10 }, { 4, 1, "-", 3 }, { 20, 3210, "-", -3190 }, { 1123, 123, "-", 1000 }, { 312, 12, "-", 300 },
                { 0, 2133, "*", 0 }, { 1, 3, "*", 3 }, { 40, 0, "*", 0 }, { 312, 312, "*", 97344 }, { 10, 10, "*", 100 },
        });
    }

    @Before
    public void setUp(){
        this.integerComputer = new IntegerComputer();
    }

    @Test
    public void testSum(){
        assertEquals(this.integerComputer.performOpperation(op1, op2, opperator), result);
    }
}

//parametized junit tests
