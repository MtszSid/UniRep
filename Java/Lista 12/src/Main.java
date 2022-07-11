import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class Main {

    public static final UnaryOperator<Integer> collatz = a -> a == 1
            ? 1
            :(a % 2 == 0
                ? Main.collatz.apply(a / 2)
                : Main.collatz.apply(3 * a + 1)) + 1;

    public static final BinaryOperator<Integer> euklides = (a, b) -> b == 0
            ? a
            : Main.euklides.apply(b, a % b);


    public static void main(String[] args) {
        czesc1("out/production/Lista12/cz1.txt");
        czesc2("out/production/Lista12/cz2.txt");
        System.out.println(collatz.apply(10));
        System.out.println(collatz.apply(27));

        System.out.println(euklides.apply(128,357));
        System.out.println(euklides.apply(5*7*9*4*11, 13*7*9*17));

    }

    public static void czesc1(String path){
        ArrayList<Integer> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (String ln = br.readLine(); ln != null; ln = br.readLine()) {
                if(Pattern.matches("\s*(10{9}|[1-9][0-9]{0,8})?+\s*(//.*)?+", ln)){
                    if(ln.equals("") || Pattern.matches("\s*|\s*//.*", ln)){
                        continue;
                    }
                    String s = ln.split("//")[0].trim();
                    Integer i = Integer.valueOf(s.replaceAll("\s", ""));
                    lista.add(i);

                }
                else{
                    throw new InvalidLine(ln);
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }
        System.out.println("posortowane:");
        lista.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        //lista.stream().sorted((a,b) -> b.compareTo(a)).forEach(System.out::println);

        System.out.println();
        System.out.println("Pierwsze:");
        lista.stream().filter(a -> {
            int lim = (int)Math.ceil(Math.sqrt(a));
            if(a<2){
                return false;
            }
            if(a % 2 == 0){
                return false;
            }
            for(int i = 3; i <= lim; i++){
                if(a % i == 0){
                    return false;
                }
            }
            return true;
        }).forEach(System.out::println);

        System.out.println();
        System.out.println("suma:");
        System.out.println(lista.stream().filter(a -> a < 1000).reduce(0,(a, b) -> a + b));

        System.out.println();
        System.out.println("Podzielne przez 7:");
        System.out.println(lista.stream().filter(a -> a % 7 == 0).count());

        System.out.println();
    }

    public static void czesc2(String path){
        ArrayList<Trojkat> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (String ln = br.readLine(); ln != null; ln = br.readLine()) {
                if(Pattern.matches("\s*(([1-9][0-9]*(\\.[0-9]*)?+|0\\.[0-9])\s+([1-9][0-9]*(\\.[0-9]*)?+|0\\.[0-9])\s+([1-9][0-9]*(\\.[0-9]*)?+|0\\.[0-9]))?+\s*(//.*)?+", ln)){
                    if(ln.equals("") || Pattern.matches("\s*|\s*//.*", ln)){
                        continue;
                    }
                    String s = ln.split("//")[0];
                    String[] a = s.trim().split("\s");
                    Trojkat t = new Trojkat(Float.valueOf(a[0]), Float.valueOf(a[1]), Float.valueOf(a[2]));
                    lista.add(t);
                }
                else{
                    throw new InvalidLine(ln);
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

        System.out.println();
        System.out.println("Posortowane");
        lista.stream().sorted(Comparator.comparing(Trojkat::obw)).forEach(System.out::println);

        System.out.println();
        System.out.println("Prostokątne:");
        lista.stream().filter(a -> 2 * Math.pow(Math.max(a.a, Math.max(a.b, a.c)), 2) ==
                                       Math.pow(a.a, 2) + Math.pow(a.b, 2) + Math.pow(a.c, 2))
                .forEach(System.out::println);

        System.out.println();
        System.out.println("Równoboczne:");
        lista.stream().filter(a -> Objects.equals(a.a, a.b) && Objects.equals(a.b, a.c)).forEach(System.out::println);

        System.out.println();
        System.out.println("Min i max pole:");

        System.out.println(lista.stream().map(a -> new Pair(a, a.pole())).min(Comparator.comparingDouble(a -> a.pole)).get().t);
        System.out.println(lista.stream().map(a -> new Pair(a, a.pole())).max(Comparator.comparingDouble(a -> a.pole)).get().t);

    }
}

class InvalidLine extends Exception{
    String line;
    public InvalidLine(String line){
        this.line = line;
    }
    @Override
    public String getMessage() {
        return "Nieprawidłowa linia: " + line;
    }
}

class InvalidTriangle extends Exception{
    String line;
    public InvalidTriangle(String line){
        this.line = line;
    }
    @Override
    public String getMessage() {
        return "Nieprawidłowy trójkąt: " + line;
    }
}

class Trojkat{
    public Float a,b,c;
    public Trojkat(Float a, Float b, Float c) throws InvalidTriangle {

        if(2 * Math.max(a, Math.max(b, c)) >= a + b + c){
            throw new InvalidTriangle(a + ", " + b + ", " + c);
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Trójkąt{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    public Float obw(){
        return a + b + c;
    }

    public double pole(){
        Float p = obw()/2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
class Pair{
    public Trojkat t;
    public double pole;

    public Pair(Trojkat t, double pole){
        this.t = t;
        this.pole = pole;
    }
}