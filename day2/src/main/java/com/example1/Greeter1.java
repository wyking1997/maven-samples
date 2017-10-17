package com.example1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a class.
 */
public class Greeter1 {

  /**
   * This is a constructor.
   */

  //TODO: Add javadoc comment
  public String greet(String someone) {

    Scanner scanStr = new Scanner(System.in);
    Scanner scanInt = new Scanner(System.in);

    System.out.print("Introduceti un sir de caractere: ");
    String str1 = scanStr.nextLine();

    System.out.print("Introduceti un sir de caractere: ");
    String str2 = scanStr.nextLine();

    ArrayList<Integer> integers = new ArrayList<>();
    for(int i = 0; i<str2.length();i++) {
      System.out.print(str2.charAt(i) + " --> ");
      integers.add(scanInt.nextInt());
    }

    int num = 0;
    boolean bool;

    for(int i=0; i<str2.length(); i++){
      bool = false;
      for(int j=0;j<str1.length();j++)
        if (str2.charAt(i)==str1.charAt(j)){
          num=num+j;
          bool = true;
          break;
        }
        if (bool)
          num += integers.get(i);
        else {

        }
    }

    return String.format("Hello, %s!", someone);
  }
}

