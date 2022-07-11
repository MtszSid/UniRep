package figury;

public class Trojkat {
    protected Punkt A, B, C;

    @Override
    public String toString() {
        return "Trojkat{" +
                "A=" + A +
                ", B=" + B +
                ", C=" + C +
                '}';
    }

    public Trojkat(Punkt a, Punkt b, Punkt c){
        if((a.x == b.x && a.x == c. x) ||(a.y == b.y && a.y == c. y)){
            throw new IllegalArgumentException("Punkty współliniowe.");
        }
        A = a;
        B = b;
        C = c;
    }

    public void przesun(Wektor W){
        A.przesun(W);
        B.przesun(W);
        C.przesun(W);
    }

    public void obroc(Punkt P, double kat){
        A.obroc(P, kat);
        B.obroc(P, kat);
        C.obroc(P, kat);
    }

    public void odbij(Prosta p){
        A.odbij(p);
        B.odbij(p);
        C.odbij(p);
    }

}
