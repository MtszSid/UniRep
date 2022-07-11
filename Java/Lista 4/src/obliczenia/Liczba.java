package obliczenia;

public class Liczba extends Wyrazenie{
    private int wartosc;

    public Liczba(int wartosc){
        this.wartosc = wartosc;
        priorytet = 100;
    }

    @Override
    public int oblicz() {
        return wartosc;
    }

    @Override
    public String toString() {
        return wartosc + "";
    }
}
