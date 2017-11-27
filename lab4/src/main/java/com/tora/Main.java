package com.tora;

import org.apache.commons.lang3.ObjectUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final String INPUT_FILE_NAME = "./lab4/src/main/java/com/tora/input4.txt";
    private final String OUTPUT_FILE_NAME = "./lab4/src/main/java/com/tora/valid.data";

    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();

        main.eraseFile(main.OUTPUT_FILE_NAME);
        main.restorePersones(main.INPUT_FILE_NAME).stream().forEach(el -> main.appendToFile(main.OUTPUT_FILE_NAME, el.toString()));
    }

    private List<Person> restorePersones(String inputFileName) throws FileNotFoundException {

        List<Person> result = new ArrayList<>();
        Scanner scanner = new Scanner(new File(inputFileName));
        scanner.useDelimiter("%");

        String personeInf;
        String[] p;
        while(scanner.hasNext()) {
            personeInf = scanner.next();
            p = personeInf.split("~");

            try {
                result.add(new Person(p[0] + " " + p[1] + " " + p[2], p[3], p[4]));
            } catch (Throwable throwable) {}
        }

        return result;
    }

    private void appendToFile(String fileName, String data){
        try {
            Files.write(Paths.get(fileName), data.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {}
    }

    private void eraseFile(String fileName){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();
    }
}
