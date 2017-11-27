package com.tora;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int listSize = 100000;
        String filePath = "./lab3/src/main/java/com/tora/digDecimals.serialized";

        List<BigDecimal> ls = new LinkedList<>();
        Random random = new Random();

        IntStream.rangeClosed(1, listSize)
                .forEach(e -> ls.add(new BigDecimal(Math.random())));

        Serializer.serializeBigDecimal(ls, filePath);
        Serializer.deserializeBigDecimal(listSize, filePath);
        System.exit(0);

        //SUM
        System.out.println(ls.stream().reduce(BigDecimal.ZERO, (x, y) -> x.add(y)));


//        System.out.println();
//        BigDecimal[] avg = ls.stream()
//                .map(el -> new BigDecimal[]{el, BigDecimal.ONE})
//                .reduce((a, b) -> new BigDecimal[]{a[0].add(b[0]), a[1].add(BigDecimal.ONE)})
//                .map(el -> new BigDecimal[]{el[0].divide(el[1], RoundingMode.HALF_UP)})
//                .get();
//        System.out.println(avg[0]);

        //AVG
        int counter[] = {0};
        BigDecimal average = ls.stream().reduce((avg, element) -> {
            avg.add(element);
            ++counter[0];

            if (counter[0] == ls.size())
                avg.divide(new BigDecimal(ls.size() + ""));

            return avg;
        }).get();

        System.out.println(ls);
        System.out.println("\n\n" + average + "\n\n");



        //TOP 10
        List<BigDecimal> ls2 = ls.stream().sorted().limit(ls.size() / 10).collect(Collectors.toList());
        System.out.println(ls.size() + " " + ls2.size());


    }

}