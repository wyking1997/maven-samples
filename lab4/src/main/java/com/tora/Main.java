package com.tora;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    private final String INPUT_FILE_NAME = "./lab4/src/main/java/com/tora/input4.txt";
    private final String OUTPUT_FILE_NAME = "./lab4/src/main/java/com/tora/valid.data";

    public static void main(String[] args) throws Exception {
        Main main = new Main();

//        BlockingQueue queue = new ArrayBlockingQueue(524288);
        BlockingQueue queue = new ArrayBlockingQueue(102400);

        Producer producer = new Producer(queue, main.INPUT_FILE_NAME);
        Consumer consumer = new Consumer(queue, main.OUTPUT_FILE_NAME);

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);

        t1.setName("PRODUCER");
        t2.setName("CONSUMER");

        t1.start();
        t2.start();
    }
}