package obliczenia;

public class Maksimum extends Operator2Arg{

    public Maksimum(Wyrazenie lewe, Wyrazenie prawe){
        leweWyrazenie = lewe;
        praweWyrazenie = prawe;
        priorytet = 100;
    }

    @Override
    public int oblicz() {
        return Math.max(leweWyrazenie.oblicz(), praweWyrazenie.oblicz());
    }

    @Override
    public String toString() {
        return "max(" + leweWyrazenie + ", " + praweWyrazenie + ")";
    }
}
