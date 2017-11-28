package com.tora;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    protected BlockingQueue queue;
    private String inputFileName;
    private long couter = 0l;

    public Producer(BlockingQueue queue, String inputFileName) {
        this.queue = queue;
        this.inputFileName = inputFileName;
    }

    public void run() {
        try {
            Scanner scanner = new Scanner(new File(inputFileName));
            scanner.useDelimiter("%");

            String personeInf;
            String[] p;
            Person person;
            while (scanner.hasNext()) {
                personeInf = scanner.next();
                p = personeInf.split("~");
                try {
                    person = new Person(p[0] + " " + p[1] + " " + p[2], p[3], p[4]);
                    queue.put(person);
                    System.out.println("producer: " + (couter++));
                } catch (Throwable t) {}
            }
            queue.put(false);
        } catch (Throwable t){
            t.printStackTrace();
        }
    }
}