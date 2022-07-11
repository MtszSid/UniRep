package obliczenia;

public class Modul extends Operator2Arg{
    public Modul(Wyrazenie lewe, Wyrazenie prawe){
        leweWyrazenie = lewe;
        praweWyrazenie = prawe;
        priorytet = 10;
    }

    @Override
    public int oblicz() {
        return leweWyrazenie.oblicz() % praweWyrazenie.oblicz();
    }

    @Override
    public String toString() {
        String lewy = (leweWyrazenie.priorytet >= priorytet ?
                praweWyrazenie.toString() : "(" + praweWyrazenie.toString() + ")");
        String prawy = (praweWyrazenie.priorytet >= priorytet ?
                praweWyrazenie.toString() : "(" + praweWyrazenie.toString() + ")");
        return lewy + "%" + prawy;
    }
}
