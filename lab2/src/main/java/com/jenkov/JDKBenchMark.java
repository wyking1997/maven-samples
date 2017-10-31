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
public class JDKBenchMark {
    @Param({"1", "10", "50"})
    public int size;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JDKBenchMark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    // ADD METHODS
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

    //CONTAINS METHODS
    @Benchmark
    public void arrayListContains(Blackhole consummer, ArrayListRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> consummer.consume(state.list.contains(state.getRandomElement())));
    }
    @Benchmark
    public void treeSetContains(Blackhole consummer, TreeSetRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> consummer.consume(state.list.contains(state.getRandomElement())));
    }
    @Benchmark
    public void hashSetContains(Blackhole consummer, HashSetRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> consummer.consume(state.list.contains(state.getRandomElement())));
    }

    //REMOVE METHODS
    @Benchmark
    public void arrayListRemove(ArrayListRepo state){
        for (int i = 0; i < size; i++){
            state.list.remove(state.getExisting());
        }
    }
    @Benchmark
    public void treeSetRemove(TreeSetRepo state){
        for (int i = 0; i < size; i++){
            state.list.remove(state.getExisting());
        }
    }
    @Benchmark
    public void hashSetRemove(HashSetRepo state){
        for (int i = 0; i < size; i++){
            state.list.remove(state.getExisting());
        }
    }

    // CONCURENT HASH MAP BASED REPOSITORY
    @Benchmark
    public void concurentHashMapAdd(ConcurrentHashMapRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.add(new Order(el, 10, 10)));
    }
    @Benchmark
    public void concurentHashMapContains(Blackhole consummer, ConcurrentHashMapRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> consummer.consume(state.list.contains(state.getRandomElement())));
    }
    @Benchmark
    public void concurentHashMapRemove(ConcurrentHashMapRepo state){
        for (int i = 0; i < size; i++){
            state.list.remove(state.getExisting());
        }
    }

    // Eclipse Collections Bag Repo
    @Benchmark
    public void gcConcurentHashMapAdd(GcBagRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.add(new Order(el, 10, 10)));
    }
    @Benchmark
    public void gcConcurentHashMapContains(Blackhole consummer, GcBagRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> consummer.consume(state.list.contains(state.getRandomElement())));
    }
    @Benchmark
    public void gcConcurentHashMapRemove(GcBagRepo state){
        for (int i = 0; i < size; i++){
            state.list.remove(state.getExisting());
        }
    }

    // Koloboke hash map with primitive int
    @Benchmark
    public void kolobokeHashMapAdd(GcBagRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.add(new Order(el, 10, 10)));
    }
    @Benchmark
    public void kolobokeHashMapContains(Blackhole consummer, GcBagRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> consummer.consume(state.list.contains(state.getRandomElement())));
    }
    @Benchmark
    public void kolobokeHashMapRemove(GcBagRepo state){
        for (int i = 0; i < size; i++){
            state.list.remove(state.getExisting());
        }
    }

    // Trove4j hash map with primitive int
    @Benchmark
    public void troveHashSetAdd(TroveHashRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.add(new Order(el, 10, 10)));
    }
    @Benchmark
    public void troveHashSetContains(Blackhole consummer, TroveHashRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> consummer.consume(state.list.contains(state.getRandomElement())));
    }
    @Benchmark
    public void troveHashSetRemove(TroveHashRepo state){
        for (int i = 0; i < size; i++){
            state.list.remove(state.getExisting());
        }
    }

    // HastUtil hash set
    @Benchmark
    public void fastUtilHashSetAdd(FastUtilHashRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> state.list.add(new Order(el, 10, 10)));
    }
    @Benchmark
    public void fastUtilHashSetContains(Blackhole consummer, FastUtilHashRepo state){
        IntStream.rangeClosed(0, size)
                .forEach(el -> consummer.consume(state.list.contains(state.getRandomElement())));
    }
    @Benchmark
    public void fastUtilHashSetRemove(FastUtilHashRepo state){
        for (int i = 0; i < size; i++){
            state.list.remove(state.getExisting());
        }
    }

    //java -jar target/benchmarks.jar JMHSample_26 -f 1

    @State(Scope.Benchmark)
    public static class ArrayListRepo{
        ArrayListBasedRepositpory<Order> list;
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
        HashSetBasedRepository<Order> list;
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
        TreeSetBasedRepository<Order> list;
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
    @State(Scope.Benchmark)
    public static class ConcurrentHashMapRepo{
        ConcurentHashMapBasedRepository<Order> list;
        Random random = new Random();

        @Setup(Level.Invocation)
        public void doSetup() {
            list = new ConcurentHashMapBasedRepository();
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
    public static class GcBagRepo{
        GcBagBasedRepo<Order> list;
        Random random = new Random();

        @Setup(Level.Invocation)
        public void doSetup() {
            list = new GcBagBasedRepo<>();
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
    public static class KolobokeHashRepo{
        KolobokeHashBasedRepo<Order> list;
        Random random = new Random();

        @Setup(Level.Invocation)
        public void doSetup() {
            list = new KolobokeHashBasedRepo<>();
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
    public static class TroveHashRepo{
        Tove4jHashMapBasedRepository<Order> list;
        Random random = new Random();

        @Setup(Level.Invocation)
        public void doSetup() {
            list = new Tove4jHashMapBasedRepository<>();
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
    public static class FastUtilHashRepo{
        FastUtilBasedRepository<Order> list;
        Random random = new Random();

        @Setup(Level.Invocation)
        public void doSetup() {
            list = new FastUtilBasedRepository<>();
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