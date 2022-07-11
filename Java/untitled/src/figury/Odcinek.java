package figury;

public class Odcinek {
    Punkt A;
    Punkt B;

    @Override
    public String toString() {
        return "{" + A + B + '}';
    }

    public Odcinek(Punkt p, Punkt r){
        if(p.x == r.x && p.y == r.y){
            throw new IllegalArgumentException("Punkty tożsame");
        }
        A = p;
        B = r;
    }

    public void przesun(Wektor W){
        A.przesun(W);
        B.przesun(W);
    }

    public void obroc(Punkt P, double kat){
        A.obroc(P, kat);
        B.obroc(P, kat);
    }

    public void odbij(Prosta p){
        A.odbij(p);
        B.odbij(p);
    }
}
