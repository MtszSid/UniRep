package obliczenia;

public class Silnia extends Operator1Arg{

    public Silnia(Wyrazenie w){
        leweWyrazenie = w;
        priorytet = 50;
    }

    @Override
    public int oblicz() {
        int lim = leweWyrazenie.oblicz();
        int wynik = 1;
        for(int i = 1; i <= lim; i++){
            wynik *= i;
        }
        return wynik;
    }

    @Override
    public String toString() {
        if(leweWyrazenie.priorytet < priorytet) {
            return "(" + leweWyrazenie.toString() + ")!";
        }
        return leweWyrazenie.toString() + "!";
    }
}
