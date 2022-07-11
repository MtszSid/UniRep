import obliczenia.*;


public class Main {

    public static void main(String[] args) {
        Wyrazenie w = new Silnia(
                new Dodawanie(
                    new Odejmowanie(
                            new Liczba(7), new Liczba(3)),
                    new Odejmowanie(
                            new Liczba(10), new Liczba(3))));
        System.out.println(w + " = " + w.oblicz());
    }
}
