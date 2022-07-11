package obliczenia;

public class LogDyskretny extends Operator2Arg{
    public LogDyskretny(Wyrazenie lewe, Wyrazenie prawe){
        leweWyrazenie = lewe;
        praweWyrazenie = prawe;
        priorytet = 100;
    }

    @Override
    public int oblicz() {
        //TODO
        return 1;
    }

    @Override
    public String toString() {
        return "LOG(" + leweWyrazenie.toString() + ", " + praweWyrazenie.toString();
    }
}
