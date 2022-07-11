package obliczenia;

public class Rozne extends Operator2Arg{

    public Rozne(Wyrazenie lewe, Wyrazenie prawe){
        leweWyrazenie = lewe;
        praweWyrazenie = prawe;
        priorytet = 0;
    }

    @Override
    public int oblicz() {
        return (leweWyrazenie.oblicz() != praweWyrazenie.oblicz() ? 1 : 0);
    }

    @Override
    public String toString() {
        return leweWyrazenie.toString() + " != " + praweWyrazenie.toString();
    }
}
