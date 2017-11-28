package com.tora;

import java.io.FileNotFoundException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    private final String INPUT_FILE_NAME = "./lab4/src/main/java/com/tora/input4.txt";
    private final String OUTPUT_FILE_NAME = "./lab4/src/main/java/com/tora/valid.data";

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Main main = new Main();

        BlockingQueue queue = new ArrayBlockingQueue(524288);

        Producer producer = new Producer(queue, main.INPUT_FILE_NAME);
        Consumer consumer = new Consumer(queue, main.OUTPUT_FILE_NAME);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}