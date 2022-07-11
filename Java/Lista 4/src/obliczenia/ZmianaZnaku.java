package obliczenia;

public class ZmianaZnaku extends Operator1Arg{
    public ZmianaZnaku(Wyrazenie wyrazenie){
        leweWyrazenie = wyrazenie;
        priorytet = 10;
    }
    @Override
    public int oblicz() {
        return -1 * leweWyrazenie.oblicz();
    }

    @Override
    public String toString() {
        if(leweWyrazenie.priorytet > priorytet) {
            return "-(" + leweWyrazenie.toString() + ")";
        }
        return "-" + leweWyrazenie.toString();
    }
}
