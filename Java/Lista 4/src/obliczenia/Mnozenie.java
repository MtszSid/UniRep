package obliczenia;

public class Mnozenie extends Operator2Arg{

    public Mnozenie(Wyrazenie lewe, Wyrazenie prawe){
        leweWyrazenie = lewe;
        praweWyrazenie = prawe;
        priorytet = 20;
    }

    @Override
    public int oblicz() {
        return leweWyrazenie.oblicz() * praweWyrazenie.oblicz();
    }

    @Override
    public String toString() {
        String lewy = (leweWyrazenie.priorytet >= priorytet ?
                praweWyrazenie.toString() : "(" + praweWyrazenie.toString() + ")");
        String prawy = (praweWyrazenie.priorytet >= priorytet ?
                praweWyrazenie.toString() : "(" + praweWyrazenie.toString() + ")");
        return lewy + "*" + prawy;
    }
}
