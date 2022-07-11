package obliczenia;

public abstract class Wyrazenie implements Obliczalny{

    int priorytet;

    /** metoda sumująca wyrażenia */
    public static int suma (Wyrazenie... wyr) {
        int wynik = 0;

        for (Wyrazenie w: wyr) {
            wynik += w.oblicz();
        }

        return wynik;
    }
    /** metoda mnożąca wyrażenia */
    public static int iloczyn (Wyrazenie... wyr) {
        int wynik = 1;

        for (Wyrazenie w: wyr) {
            wynik *= w.oblicz();
        }

        return wynik; }

}

abstract class Operator1Arg extends Wyrazenie{
    Wyrazenie leweWyrazenie;
}

abstract class Operator2Arg extends Operator1Arg{
    Wyrazenie praweWyrazenie;
}
