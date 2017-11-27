package com.tora;

import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Serializer {

    public static void serializeBigDecimal(List<BigDecimal> ls, String filePath){
        System.out.println("Serialize list of size " + ls.size());
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            ls.forEach(big -> {
                try {
                    out.writeObject(big);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + filePath);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static List<BigDecimal> deserializeBigDecimal(int numberOfElements, String filePath){
        List<BigDecimal> result = new LinkedList<>();
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            while (numberOfElements > 0) {
                result.add((BigDecimal) in.readObject());
                numberOfElements--;
            }
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        System.out.println("Deserialized " + result.size() + " objects");
        return result;
    }
}
