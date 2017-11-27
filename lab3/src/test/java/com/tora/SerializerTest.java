package com.tora;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SerializerTest {

    private List<BigDecimal> ls;
    private final String testFile = "test.serialize";

    public SerializerTest(int size) {
        ls = new LinkedList<>();
        Random random = new Random();

        IntStream.rangeClosed(1, size)
                .forEach(e -> ls.add(new BigDecimal(Math.random())));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {1},{100},{10000},{100000}
        });
    }

    @Test
    public void testing1(){
        Serializer.serializeBigDecimal(ls, testFile);
        List<BigDecimal> deserializedElements = Serializer.deserializeBigDecimal(testFile);

        assertEquals(ls.size(),deserializedElements.size());
    }
}
