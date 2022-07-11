package obliczenia;


public class Potegowanie extends Operator2Arg{
    public Potegowanie(Wyrazenie podstawa, Wyrazenie wykladnik){
        leweWyrazenie = podstawa;
        praweWyrazenie = wykladnik;
        priorytet = 30;
    }

    @Override
    public int oblicz() {
        return (int)Math.pow(leweWyrazenie.oblicz(), praweWyrazenie.oblicz());
    }

    @Override
    public String toString() {
        String lewe = (leweWyrazenie instanceof Operator1Arg ?
                "(" + leweWyrazenie + ")"
                : leweWyrazenie.toString());
        String prawe = (praweWyrazenie instanceof Operator1Arg ?
                "(" + praweWyrazenie + ")"
                : praweWyrazenie.toString());
        return lewe + "^" + prawe;
    }

}
