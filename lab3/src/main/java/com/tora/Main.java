package com.tora;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<BigDecimal> ls = new LinkedList<>();
        Random random = new Random();

        IntStream.rangeClosed(0, 200000)
                .forEach(e -> ls.add(new BigDecimal(Math.random())));

        System.out.println(ls.stream().reduce(BigDecimal.ZERO, (x, y) -> x.add(y)));
        System.out.println();
        BigDecimal[] avg = ls.stream()
                .map(el -> new BigDecimal[]{el, BigDecimal.ONE})
                .reduce((a, b) -> new BigDecimal[]{a[0].add(b[0]), a[1].add(BigDecimal.ONE)})
                .map(el -> new BigDecimal[]{el[0].divide(el[1], RoundingMode.HALF_UP)})
                .get();
        System.out.println(avg[0]);
    }
}
