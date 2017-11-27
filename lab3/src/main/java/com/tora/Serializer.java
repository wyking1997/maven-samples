package com.tora;

import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Serializer {

    public static void serializeBigDecimal(List<BigDecimal> ls, String filePath){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeInt(ls.size());
            ls.forEach(big -> {
                try {
                    out.writeObject(big);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static List<BigDecimal> deserializeBigDecimal(String filePath){
        List<BigDecimal> result = new LinkedList<>();
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            int numberOfElements = in.readInt();
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
        return result;
    }
}
