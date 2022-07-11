package obliczenia;

public class Dodawanie extends Operator2Arg{

    public Dodawanie(Wyrazenie lewe, Wyrazenie prawe){
        leweWyrazenie = lewe;
        praweWyrazenie = prawe;
        priorytet = 15;
    }

    @Override
    public int oblicz() {
        return leweWyrazenie.oblicz() + praweWyrazenie.oblicz();
    }

    @Override
    public String toString() {
        String lewy = leweWyrazenie.toString();
        String prawy = (praweWyrazenie.priorytet >= priorytet ?
                praweWyrazenie.toString() : "(" + praweWyrazenie.toString() + ")");
        return lewy + "+" + prawy;
    }
}
