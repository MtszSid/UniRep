package obliczenia;

public class MniejszeRowne extends  Operator2Arg{

    public MniejszeRowne(Wyrazenie lewe, Wyrazenie prawe){
        leweWyrazenie = lewe;
        praweWyrazenie = prawe;
        priorytet = 0;
    }

    @Override
    public int oblicz() {
        return (leweWyrazenie.oblicz() <= praweWyrazenie.oblicz() ? 1 : 0);
    }

    @Override
    public String toString() {
        return leweWyrazenie.toString() + " <= " + praweWyrazenie.toString();
    }
}
