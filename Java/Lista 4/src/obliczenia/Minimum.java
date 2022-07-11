package obliczenia;

public class Minimum extends Operator2Arg{

    public Minimum(Wyrazenie lewe, Wyrazenie prawe){
        leweWyrazenie = lewe;
        praweWyrazenie = prawe;
        priorytet = 100;
    }

    @Override
    public int oblicz() {
        return Math.min(leweWyrazenie.oblicz(), praweWyrazenie.oblicz());
    }

    @Override
    public String toString() {
        return "min(" + leweWyrazenie + ", " + praweWyrazenie + ")";
    }
}
