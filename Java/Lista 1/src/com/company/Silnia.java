import java.math.*;
import java.util.Scanner;


public class Silnia {

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

        System.out.println(Limit + "! = " + Factorial(Limit));
    }

    static BigInteger Factorial(int Limit){
        BigInteger factorial = new BigInteger("1");


        for(Integer i = 1; i <= Limit; i++){
            factorial = factorial.multiply( new BigInteger(i.toString()));
        }
        return factorial;
    }
}
