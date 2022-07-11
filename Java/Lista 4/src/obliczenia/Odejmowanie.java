package obliczenia;

public class Odejmowanie extends Operator2Arg{
    public Odejmowanie(Wyrazenie lewe, Wyrazenie prawe){
        leweWyrazenie = lewe;
        praweWyrazenie = prawe;
        priorytet = 15;
    }

    @Override
    public String toString() {
        String lewy = leweWyrazenie.toString();
        String prawy = (praweWyrazenie.priorytet >= priorytet?
                praweWyrazenie.toString() : "(" + praweWyrazenie.toString() + ")");
        return lewy + "-" + prawy;
    }

    @Override
    public int oblicz() {
        return leweWyrazenie.oblicz() - praweWyrazenie.oblicz();
    }
}
