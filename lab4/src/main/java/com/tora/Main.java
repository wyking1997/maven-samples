package com.tora;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final String FILE_NAME = "./lab4/src/main/java/com/tora/input4.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();

        main.restorePersones(main.FILE_NAME);
    }

    public List<Person> restorePersones(String fileName) throws FileNotFoundException {

        List<Person> result = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));
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
}
