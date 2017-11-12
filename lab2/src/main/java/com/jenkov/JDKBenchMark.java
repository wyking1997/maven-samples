package com.jenkov;

import com.jenkov.model.OperationType;
import com.jenkov.model.Order;
import com.jenkov.repo.implementation.nonprimitive.*;
import com.jenkov.repo.implementation.primitive.FastUtilBasedRepository;
import com.jenkov.repo.implementation.primitive.GcIntArrayListRepo;
import com.jenkov.repo.implementation.primitive.Tove4jIntListBasedRepository;
import com.jenkov.repo.specification.nonprimitive.InMemoryRepository;
import com.jenkov.repo.specification.primitive.IntInMemoryRepository;
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
@Threads(2)
@State(Scope.Benchmark)
public class JDKBenchMark {
    @Param({"1", "10", "50", "100"})
    public static int size;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JDKBenchMark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

//    ADD METHODS
    @Benchmark
    public void hashSetAdd(HashSetRepo state){
        state.type = OperationType.ADD;
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.add(new Order(state.id + el, 10, 10)));
    }
    @Benchmark
    public void treeSetAdd(TreeSetRepo state){
        state.type = OperationType.ADD;
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.add(new Order(state.id + el, 10, 10)));
    }
    @Benchmark
    public void arrayListAdd(ArrayListRepo state){
        state.type = OperationType.ADD;
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.add(new Order(state.id + el, 10, 10)));
    }

    //CONTAINS METHODS
    @Benchmark
    public void arrayListContains(Blackhole consummer, ArrayListRepo state){
        state.type = OperationType.CONTAINS;
        IntStream.rangeClosed(0, size)
                .forEach(el -> consummer.consume(state.list.contains(state.getRandomElement())));
    }
    @Benchmark
    public void treeSetContains(Blackhole consummer, TreeSetRepo state){
        state.type = OperationType.CONTAINS;
        IntStream.rangeClosed(0, size)
                .forEach(el -> consummer.consume(state.list.contains(state.getRandomElement())));
    }
    @Benchmark
    public void hashSetContains(Blackhole consummer, HashSetRepo state){
        state.type = OperationType.CONTAINS;
        IntStream.rangeClosed(0, size)
                .forEach(el -> consummer.consume(state.list.contains(state.getRandomElement())));
    }

    //REMOVE METHODS
    @Benchmark
    public void arrayListRemove(ArrayListRepo state){
        state.type = OperationType.REMOVE;
        state.id = state.random.nextInt(state.list.getAll().size() - size);
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.remove(new Order(state.id + el, 10, 10)));
    }
    @Benchmark
    public void treeSetRemove(TreeSetRepo state){
        state.type = OperationType.REMOVE;
        state.id = state.random.nextInt(state.list.getAll().size() - size);
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.remove(new Order(state.id + el, 10, 10)));
    }
    @Benchmark
    public void hashSetRemove(HashSetRepo state){
        state.type = OperationType.REMOVE;
        state.id = state.random.nextInt(state.list.getAll().size() - size);
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.remove(new Order(state.id + el, 10, 10)));
    }

//    CONCURENT HASH MAP BASED REPOSITORY
    @Benchmark
    public void concurentHashMapAdd(ConcurrentHashMapRepo state){
        state.type = OperationType.ADD;
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.add(new Order(state.id + el, 10, 10)));
    }
    @Benchmark
    public void concurentHashMapContains(Blackhole consummer, ConcurrentHashMapRepo state){
        state.type = OperationType.CONTAINS;
        IntStream.rangeClosed(0, size)
                .forEach(el -> consummer.consume(state.list.contains(state.getRandomElement())));
    }
    @Benchmark
    public void concurentHashMapRemove(ConcurrentHashMapRepo state){
        state.type = OperationType.REMOVE;
        state.id = state.random.nextInt(state.list.getAll().size() - size);
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.remove(new Order(state.id + el, 10, 10)));
    }

//    Eclipse Collections IntArrayListRepo
    @Benchmark
    public void gcIntArrayListAdd(GcIntArrayLsRepo state){
        state.type = OperationType.ADD;
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.repo.add(state.element + el));
    }
    @Benchmark
    public void gcIntArrayListContains(Blackhole consummer, GcIntArrayLsRepo state){
        state.type = OperationType.CONTAINS;
        IntStream.rangeClosed(0, size)
                .forEach(el -> consummer.consume(state.repo.contains(state.getRandomElement())));
    }
    @Benchmark
    public void gcIntArrayListRemove(GcIntArrayLsRepo state){
        state.type = OperationType.REMOVE;
        state.element = state.random.nextInt(state.repo.getSize() - size);
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.repo.remove(el + state.element));
    }

//    Trove4j List with primitive int
    @Benchmark
    public void troveIntListAdd(TroveHashRepo state){
        state.type = OperationType.ADD;
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.repo.add(el + state.element));
    }
    @Benchmark
    public void troveIntListContains(Blackhole consummer, TroveHashRepo state){
        state.type = OperationType.CONTAINS;
        IntStream.rangeClosed(0, size)
                .forEach(el -> consummer.consume(state.repo.contains(state.getRandomElement())));
    }
    @Benchmark
    public void troveIntListRemove(TroveHashRepo state){
        state.type = OperationType.REMOVE;
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.repo.remove(el + state.element));
    }

//    HastUtil IntList
    @Benchmark
    public void fastUtilIntListAdd(FastUtilHashRepo state){
        state.type = OperationType.ADD;
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.repo.add(state.element + el));
    }
    @Benchmark
    public void fastUtilIntListContains(Blackhole consummer, FastUtilHashRepo state){
        state.type = OperationType.CONTAINS;
        IntStream.rangeClosed(0, size)
                .forEach(el -> consummer.consume(state.repo.contains(state.getRandomElement())));
    }
    @Benchmark
    public void fastUtilIntListRemove(FastUtilHashRepo state){
        state.type = OperationType.REMOVE;
        state.element = state.random.nextInt(state.repo.getSize() - size);
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.repo.remove(el + state.element));
    }

    //java -jar target/benchmarks.jar JMHSample_26 -f 1
    public static abstract class AbstactState{
        protected InMemoryRepository<Order> list;
        protected Random random = new Random();
        protected int id;
        protected OperationType type;

        @Setup(Level.Invocation)
        public void doSetuppppppppppp() {
            id = 2000000;
        }

        @TearDown(Level.Invocation)
        public void doTearDownnnnnnnnn() {
            switch (type){
                case ADD: {
                    IntStream.rangeClosed(0, JDKBenchMark.size)
                            .forEach( el -> {
                                list.remove(new Order(el + id, 10, 10));
                            });
                    return;
                }
                case REMOVE: {
                    IntStream.rangeClosed(0, JDKBenchMark.size)
                            .forEach( el -> {
                                list.add(new Order(el + id, 10, 10));
                            });
                    return;
                }
                case CONTAINS: {return;}
                default: return;
            }
        }

        @TearDown(Level.Iteration)
        public void doTearDown2() {
            list = null;
            System.gc();
        }

        public Order getRandomElement(){
            return (random.nextInt(100) > 10 ? new Order(list.getAll().get(random.nextInt(list.getAll().size())).getId(), 1,1)
                    : new Order(random.nextInt(), 10, 10));
        }
    }

    public static abstract class AbstactIntState{
        protected IntInMemoryRepository repo;
        protected Random random = new Random();
        protected int element;
        protected OperationType type;

        @Setup(Level.Invocation)
        public void doSetuppppppppppp() {
            element = 2000000;
        }

        @TearDown(Level.Invocation)
        public void doTearDownnnnnnnnn() {
            switch (type){
                case ADD: {
                    IntStream.rangeClosed(0, JDKBenchMark.size).forEach(el -> repo.remove(el));
                    return;
                }
                case REMOVE: {
                    IntStream.rangeClosed(0, JDKBenchMark.size).forEach(el -> repo.add(el));
                    return;
                }
                case CONTAINS: {return;}
                default: return;
            }
        }

        @TearDown(Level.Iteration)
        public void doTearDown2() {
            repo = null;
            System.gc();
        }

        public int getRandomElement(){
            return repo.getRandomElement(random);
        }
    }


    @State(Scope.Benchmark)
    public static class ArrayListRepo extends AbstactState{
        @Setup(Level.Iteration)
        public void doSetup() {
            list = new ArrayListBasedRepositpory();
            IntStream.rangeClosed(0, 20000)
                    .forEach(el -> list.add(new Order(el, 10, 10)));
        }
    }

    @State(Scope.Benchmark)
    public static class HashSetRepo extends AbstactState{
        @Setup(Level.Iteration)
        public void doSetup() {
            list = new HashSetBasedRepository<>();
            IntStream.rangeClosed(0, 20000)
                    .forEach(el -> list.add(new Order(el, 10, 10)));
        }
    }

    @State(Scope.Benchmark)
    public static class TreeSetRepo extends AbstactState{
        @Setup(Level.Iteration)
        public void doSetup() {
            list = new TreeSetBasedRepository();
            IntStream.rangeClosed(0, 20000)
                    .forEach(el -> list.add(new Order(el, 10, 10)));
        }
    }

    @State(Scope.Benchmark)
    public static class ConcurrentHashMapRepo extends AbstactState{
        @Setup(Level.Iteration)
        public void doSetup() {
            list = new ConcurentHashMapBasedRepository();
            IntStream.rangeClosed(0, 20000)
                    .forEach(el -> list.add(new Order(el, 10, 10)));
        }
    }

    @State(Scope.Benchmark)
    public static class GcIntArrayLsRepo extends AbstactIntState{
        @Setup(Level.Iteration)
        public void doSetup(){
            repo = new GcIntArrayListRepo();
            IntStream.rangeClosed(0, 20000).forEach(el -> repo.add(el));
        }
    }

    @State(Scope.Benchmark)
    public static class TroveHashRepo extends AbstactIntState{
        @Setup(Level.Iteration)
        public void doSetup() {
            repo = new Tove4jIntListBasedRepository();
            IntStream.rangeClosed(0, 20000).forEach(el -> repo.add(el));
        }
    }

    @State(Scope.Benchmark)
    public static class FastUtilHashRepo extends AbstactIntState{
        @Setup(Level.Iteration)
        public void doSetup() {
            repo = new FastUtilBasedRepository();
            IntStream.rangeClosed(0, 20000).forEach(el -> repo.add(el));
        }
    }
}