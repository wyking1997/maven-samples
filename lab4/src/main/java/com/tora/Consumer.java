package com.tora;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    protected BlockingQueue queue = null;
    private String fileName;
    private long couter = 0l;

    public Consumer(BlockingQueue queue, String fileName) {
        this.queue = queue;
        this.fileName = fileName;
        this.eraseOutputFile();
    }

    public void run() {
        try{
            Object obj = queue.take();
            while (!(obj instanceof Boolean)){
                this.appendToOutputFile(obj.toString());
                obj = queue.take();
                System.out.println("consumer: " + (couter++));
            }
        } catch (Throwable t){
            t.printStackTrace();
        }
    }

    private void appendToOutputFile(String data){
        try {
            Files.write(Paths.get(fileName), data.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eraseOutputFile(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName);
            writer.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally{
            writer.close();
        }
    }
}