package com.jenkov;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class MyBenchMark {
    @Param({"1", "10"})
    public int size;

    //ADD METHODS
    @Benchmark
    public void hashSetAdd(HashSetRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.add(new Order(el, 10, 10)));
    }
    @Benchmark
    public void treeSetAdd(TreeSetRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.add(new Order(el, 10, 10)));
    }
    @Benchmark
    public void arrayListAdd(ArrayListRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.add(new Order(el, 10, 10)));
    }



    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MyBenchMark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    //java -jar target/benchmarks.jar JMHSample_26 -f 1

    @State(Scope.Benchmark)
    public static class ArrayListRepo{
        ArrayListBasedRepositpory<Order> list = new ArrayListBasedRepositpory<>();
        private Random random = new Random();

        @Setup(Level.Invocation)
        public void doSetup() {
            list = new ArrayListBasedRepositpory();
            IntStream.rangeClosed(0, 20000)
                    .forEach(el -> list.add(new Order(el, 10, 10)));
        }

        @TearDown(Level.Invocation)
        public void doTearDown() {
            list = null;
            System.gc();
        }

        public Order getRandomElement(){
            return (random.nextInt(100) > 10 ? new Order(list.getAll().get(random.nextInt(list.getAll().size())).getId(), 1,1)
                    : new Order(random.nextInt(), 10, 10));
        }
        public Order getExisting(){
            return list.getAll().get(random.nextInt(list.getAll().size()));
        }
    }
    @State(Scope.Benchmark)
    public static class HashSetRepo{
        HashSetBasedRepository<Order> list = new HashSetBasedRepository<>();
        Random random = new Random();

        @Setup(Level.Invocation)
        public void doSetup() {
            list = new HashSetBasedRepository<>();
            IntStream.rangeClosed(0, 20000)
                .forEach(el -> list.add(new Order(el, 10, 10)));
        }

        @TearDown(Level.Invocation)
        public void doTearDown() {
            list = null;
            System.gc();
        }

        public Order getRandomElement(){
            return (random.nextInt(100) > 10 ? new Order(list.getAll().get(random.nextInt(list.getAll().size())).getId(), 1,1)
                    : new Order(random.nextInt(), 10, 10));
        }
        public Order getExisting(){
            return list.getAll().get(random.nextInt(list.getAll().size()));
        }
    }
    @State(Scope.Benchmark)
    public static class TreeSetRepo{
        TreeSetBasedRepository<Order> list = new TreeSetBasedRepository<>();
        Random random = new Random();

        @Setup(Level.Invocation)
        public void doSetup() {
            list = new TreeSetBasedRepository();
            IntStream.rangeClosed(0, 20000)
                    .forEach(el -> list.add(new Order(el, 10, 10)));
        }

        @TearDown(Level.Invocation)
        public void doTearDown() {
            list = null;
            System.gc();
        }

        public Order getRandomElement(){
            return (random.nextInt(100) > 10 ? new Order(list.getAll().get(random.nextInt(list.getAll().size())).getId(), 1,1)
                    : new Order(random.nextInt(), 10, 10));
        }
        public Order getExisting(){
            return list.getAll().get(random.nextInt(list.getAll().size()));
        }
    }

}


