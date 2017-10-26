package com.jenkov;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 20, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Benchmark)
public class MyBenchMark {
    @Param({"1", "50", "100", "200", "1000", "10000"})
    public int size;

    @Benchmark
    public void array_add(Blackhole consummer,MyState state){
        ArrayListBasedRepositpory<Order> orders = new ArrayListBasedRepositpory<>();

        for(int i=0; i<size;i++){
            orders.add(new Order(i,10,10));
        }

        consummer.consume(orders);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MyBenchMark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    //java -jar target/benchmarks.jar JMHSample_26 -f 1

    @State(Scope.Thread)
    class MyState{
        //setUp
        //tailDown
    }

}


