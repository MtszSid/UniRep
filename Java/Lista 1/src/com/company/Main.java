package com.company;
import java.math.*;
import java.util.Scanner;
import java.lang.String;

public class Main {

    public static void main(String[] args) {

        System.out.println("Podaj liczbę:");

        Scanner scanner = new Scanner(System.in);
        int Limit = scanner.nextInt();
        scanner.close();

        if(Limit < 0){
            System.err.println("Argument za mały.");
            System.exit(0);
        }
        if(Limit > 100){
            System.err.println("Argument za duży.");
            System.exit(0);
        }

        BigInteger Factorial = new BigInteger("1");


        for(int i = 1; i <= Limit; i++){
            Factorial = Factorial.multiply(new BigInteger(String.valueOf(i)));
        }

        System.out.println(Limit + "! = " + Factorial);
    }
}
